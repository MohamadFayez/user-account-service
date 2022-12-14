package com.blockchain.accountservice.payload.mapper;

import com.blockchain.accountservice.entity.User;
import com.blockchain.accountservice.payload.request.SignupRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toEntity(SignupRequest signupRequest);
}
