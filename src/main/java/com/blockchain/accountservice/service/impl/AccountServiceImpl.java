package com.blockchain.accountservice.service.impl;


import com.blockchain.accountservice.constant.ErrorCodes;
import com.blockchain.accountservice.entity.Account;
import com.blockchain.accountservice.entity.Transaction;
import com.blockchain.accountservice.exception.BusinessException;
import com.blockchain.accountservice.payload.request.AccountRequest;
import com.blockchain.accountservice.payload.request.Beneficiary;
import com.blockchain.accountservice.payload.response.UserResponse;
import com.blockchain.accountservice.repository.AccountRepository;
import com.blockchain.accountservice.service.AccountService;
import com.blockchain.accountservice.service.TransactionService;
import com.blockchain.accountservice.service.UserService;
import com.blockchain.accountservice.type.TransactionType;
import com.blockchain.accountservice.type.TransferStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserService userService;
    @Autowired
    TransactionService transactionService;

    @Override
    public Account createAccount(AccountRequest accountRequest) {
        UserResponse userResponse = userService.findByEmailOrPhone(accountRequest.getEmail(), accountRequest.getPhone());

        String accountNo = String.valueOf(new Random().nextInt(9000000) + 1000000);
        Account account = new Account(userResponse.getUserId(), accountNo, accountRequest.getCurrency(), accountRequest.getBalance());
        account = accountRepository.save(account);

        return account;
    }

    @Override
    public Account fundAccount(String accountNo, Double amount) {
        Account account = getAccountByAccountNo(accountNo);
        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
        account = accountRepository.save(account);
        Transaction transaction = new Transaction(account.getAccountNo(), null, amount, account.getCurrency(), TransactionType.FUND, TransferStatus.SUCCESS);
        transactionService.addTransaction(transaction);
        return account;
    }

    @Override
    public Account withdrawFromAccount(String accountNo, Double amount) {
        Account account = getAccountByAccountNo(accountNo);
        if (amount > account.getBalance()) {
            throw new BusinessException(ErrorCodes.ACCOUNT_HAVE_NOT_ENOUGH_BALANCE);
        }
        double newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);
        account = accountRepository.save(account);

        Transaction transaction = new Transaction(account.getAccountNo(), null, amount, account.getCurrency(), TransactionType.WITHDRAW, TransferStatus.SUCCESS);
        transactionService.addTransaction(transaction);
        return account;
    }

    @Override
    public void transferToAccounts(String userAccount, List<Beneficiary> beneficiaries) {
        if (!beneficiaries.isEmpty()) {
            List<String> accounts = beneficiaries.stream().map(Beneficiary::getAccount).collect(Collectors.toList());

            List<String> uniqueAccounts = accounts.stream().distinct().collect(Collectors.toList());

            if (uniqueAccounts != null && uniqueAccounts.size() > 10) {
                throw new BusinessException(ErrorCodes.CAN_NOT_TRANSFER_MORE_THAN_TEN);
            }

            beneficiaries.parallelStream().forEach(beneficiary -> {
                withdrawFromAccount(userAccount, beneficiary.getAmount());
                fundAccount(beneficiary.getAccount(), beneficiary.getAmount());

                Transaction transaction = new Transaction(userAccount, beneficiary.getAccount(), beneficiary.getAmount(), beneficiary.getCurrency(), TransactionType.TRANSFER, TransferStatus.SUCCESS);
                transactionService.addTransaction(transaction);
            });
        }
    }

    @Override
    public List<String> getUserAccounts(String userId) {
        List<Account> userAccounts = accountRepository.findByUserId(userId);
        return userAccounts.stream().map(Account::getAccountNo).collect(Collectors.toList());
    }

    private Account getAccountByAccountNo(String accountNo) {
        Optional<Account> optionalAccount = accountRepository.findByAccountNo(accountNo);
        if (optionalAccount.isPresent()) {
            return optionalAccount.get();
        } else {
            throw new BusinessException(ErrorCodes.ACCOUNT_NO_NOT_EXISTS);
        }
    }

    @Scheduled(cron = "0 0/30 * * * *")
    public void accrueUserBalance() {
        List<Account> allAccounts = accountRepository.findAll();
        if (!allAccounts.isEmpty()) {
            allAccounts.stream().forEach(account -> {
                account.setBalance((account.getBalance() * 0.25) + account.getBalance());
                accountRepository.save(account);
            });

        }
    }


}
