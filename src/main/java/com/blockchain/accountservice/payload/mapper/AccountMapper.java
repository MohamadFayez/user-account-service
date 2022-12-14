package com.blockchain.accountservice.payload.mapper;

import com.blockchain.accountservice.entity.Account;
import com.blockchain.accountservice.payload.request.AccountRequest;
import com.blockchain.accountservice.payload.response.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    Account toEntity(AccountRequest accountRequest);


    AccountResponse toViewModel(Account account);
}
