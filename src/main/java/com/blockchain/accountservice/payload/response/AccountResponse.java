package com.blockchain.accountservice.payload.response;


import com.blockchain.accountservice.type.Currency;
import lombok.Data;

import java.io.Serializable;

@Data
public class AccountResponse implements Serializable {
    private Long accountId;
    private String userId;
    private String accountNo;
    private Currency currency;
    private Double balance;

}
