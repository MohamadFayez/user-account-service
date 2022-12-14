package com.blockchain.accountservice.controller;


import com.blockchain.accountservice.common.APIResponse;
import com.blockchain.accountservice.constant.Constants;
import com.blockchain.accountservice.entity.Transaction;
import com.blockchain.accountservice.service.TransactionService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("${app.config.transaction-service.base-uri}")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("${app.config.transaction-service.api.user-transaction}")
    @Tag(name = "get user transactions")
    @ApiResponse(responseCode = "200", description = "Success")
    public APIResponse getUserTransactions(@Valid @PathVariable(name = "userId") String userId) {
        List<Transaction> userTransactions = transactionService.getUserTransactions(userId);
        return APIResponse.builder().code(Constants.OK).data(userTransactions).message(Constants.SUCCESS).build();
    }

}
