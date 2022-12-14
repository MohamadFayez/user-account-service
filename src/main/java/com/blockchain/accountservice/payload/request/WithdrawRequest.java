package com.blockchain.accountservice.payload.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WithdrawRequest {
    @NotNull
    private String accountNo;
    @NotNull
    private Double amount;
}
