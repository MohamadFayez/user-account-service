package com.blockchain.accountservice.payload.request;

import com.blockchain.accountservice.type.Currency;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
public class Beneficiary {
    @NotNull
    private String account;
    @NotNull
    private double amount;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Currency currency;
}
