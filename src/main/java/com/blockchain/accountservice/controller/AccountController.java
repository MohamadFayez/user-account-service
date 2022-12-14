package com.blockchain.accountservice.controller;


import com.blockchain.accountservice.common.APIResponse;
import com.blockchain.accountservice.constant.Constants;
import com.blockchain.accountservice.entity.Account;
import com.blockchain.accountservice.payload.mapper.AccountMapper;
import com.blockchain.accountservice.payload.request.AccountRequest;
import com.blockchain.accountservice.payload.request.FundRequest;
import com.blockchain.accountservice.payload.request.TransferRequest;
import com.blockchain.accountservice.payload.request.WithdrawRequest;
import com.blockchain.accountservice.payload.response.AccountResponse;
import com.blockchain.accountservice.service.AccountService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("${app.config.account-service.base-uri}")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;

    @PostMapping("${app.config.account-service.api.create}")
    @Tag(name = "create user account service")
    @ApiResponse(responseCode = "200", description = "Success")
    public APIResponse createAccount(@RequestBody @Valid AccountRequest accountRequest) {
        Account account = accountService.createAccount(accountRequest);
        AccountResponse accountResponse = accountMapper.toViewModel(account);
        return APIResponse.builder().code(Constants.OK).data(accountResponse).message(Constants.SUCCESS).build();
    }

    @PostMapping("${app.config.account-service.api.fund}")
    @Tag(name = "fund user account service")
    @ApiResponse(responseCode = "200", description = "Success")
    public APIResponse fundAccount(@Valid @RequestBody FundRequest fundRequest) {
        Account account = accountService.fundAccount(fundRequest.getAccountNo(), fundRequest.getAmount());
        AccountResponse accountResponse = accountMapper.toViewModel(account);
        return APIResponse.builder().code(Constants.OK).data(accountResponse).message(Constants.SUCCESS).build();
    }

    @PostMapping("${app.config.account-service.api.withdraw}")
    @Tag(name = "withdraw from user account service")
    @ApiResponse(responseCode = "200", description = "Success")
    public APIResponse withdrawFromAccount(@Valid @RequestBody WithdrawRequest withdrawRequest) {
        Account account = accountService.withdrawFromAccount(withdrawRequest.getAccountNo(), withdrawRequest.getAmount());
        AccountResponse accountResponse = accountMapper.toViewModel(account);
        return APIResponse.builder().code(Constants.OK).data(accountResponse).message(Constants.SUCCESS).build();
    }

    @PostMapping("${app.config.account-service.api.transfer}")
    @Tag(name = "transfer from user account service to other users")
    @ApiResponse(responseCode = "200", description = "Success")
    public APIResponse transferToAccounts(@Valid @RequestBody TransferRequest transferRequest) {
        accountService.transferToAccounts(transferRequest.getSenderAccount(), transferRequest.getBeneficiaries());
        return APIResponse.builder().code(Constants.OK).message(Constants.SUCCESS).build();
    }

    @PostMapping("${app.config.account-service.api.user-accounts}")
    @Tag(name = "get user accounts")
    @ApiResponse(responseCode = "200", description = "Success")
    public APIResponse getUserAccounts(@Valid @RequestHeader(value="userId") String userId) {
      List<String> userAccounts =  accountService.getUserAccounts(userId);
        return APIResponse.builder().code(Constants.OK).data(userAccounts).message(Constants.SUCCESS).build();
    }

}
