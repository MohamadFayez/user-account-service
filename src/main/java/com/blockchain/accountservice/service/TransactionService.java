package com.blockchain.accountservice.service;

import com.blockchain.accountservice.entity.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getUserTransactions(String userId);

    void addTransaction(Transaction transaction);
}
