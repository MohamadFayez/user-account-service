package com.blockchain.accountservice.repository;

import com.blockchain.accountservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUserAccountInOrBeneficiaryAccountIn(List<String> userAccounts, List<String> userAccounts1);
}
