package com.blockchain.accountservice.service.impl;

import com.blockchain.accountservice.entity.Transaction;
import com.blockchain.accountservice.repository.TransactionRepository;
import com.blockchain.accountservice.service.AccountService;
import com.blockchain.accountservice.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountService accountService;

    @Override
    public List<Transaction> getUserTransactions(String userId) {

        List<String> userAccounts = accountService.getUserAccounts(userId);
        List<Transaction> userTransactions = transactionRepository.findByUserAccountInOrBeneficiaryAccountIn(userAccounts, userAccounts);
        return userTransactions;
    }


    @Override
    public void addTransaction(Transaction transaction) {
        transaction.setTransactionDate(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

}
