package com.blockchain.accountservice.payload.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class TransferRequest {

    @NotNull
    private String senderAccount;

    @NotNull
    private List<Beneficiary> beneficiaries;

}
