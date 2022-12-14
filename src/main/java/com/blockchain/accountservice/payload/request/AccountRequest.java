package com.blockchain.accountservice.payload.request;

import com.blockchain.accountservice.type.Currency;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class AccountRequest implements Serializable {

    @NotNull
    private String userId;
    @NotNull
    private String email;
    @NotNull
    private String phone;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private double balance = 0.0;


}
