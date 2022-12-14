package com.blockchain.accountservice.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class FundRequest {
    @NotBlank
    @NotNull
    private String accountNo;

    @NotNull
    private Double amount;
}
