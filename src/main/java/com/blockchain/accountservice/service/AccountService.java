package com.blockchain.accountservice.service;

import com.blockchain.accountservice.entity.Account;
import com.blockchain.accountservice.payload.request.AccountRequest;
import com.blockchain.accountservice.payload.request.Beneficiary;

import java.util.List;

public interface AccountService {
    Account createAccount(AccountRequest accountRequest);

    Account fundAccount(String accountNo, Double amount);

    Account withdrawFromAccount(String accountNo, Double amount);

    void transferToAccounts(String senderAccount, List<Beneficiary> beneficiaries);

    List<String> getUserAccounts(String userId);
}
