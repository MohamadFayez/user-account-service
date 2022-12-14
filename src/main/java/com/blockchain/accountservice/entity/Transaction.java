package com.blockchain.accountservice.entity;


import com.blockchain.accountservice.type.Currency;
import com.blockchain.accountservice.type.TransactionType;
import com.blockchain.accountservice.type.TransferStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "user_account")
    private String userAccount;

    @Column(name = "beneficiary_account")
    private String beneficiaryAccount;

    @Column(name = "amount")
    private double amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "transfer_status")
    private String transferStatus;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    public Transaction(String userAccount, String beneficiaryAccount, double amount, Currency currency, TransactionType transactionType,
            TransferStatus transferStatus) {

        this.userAccount = userAccount;
        this.beneficiaryAccount = beneficiaryAccount;
        this.amount = amount;
        this.currency = currency.toString();
        this.transactionType = transactionType.toString();
        this.transferStatus = transferStatus.toString();
        this.transactionDate = LocalDateTime.now();

    }

}
