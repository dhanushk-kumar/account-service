package com.maveric.accountservice.service;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.model.Account;
import com.maveric.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.maveric.accountservice.constants.Constants.getCurrentDateTime;
import static com.maveric.accountservice.utility.ModelDtoTransformer.toDto;
import static com.maveric.accountservice.utility.ModelDtoTransformer.toEntity;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository repository;
    @Override
    public AccountDto createAccount(AccountDto accountDto) {

        accountDto.setCreatedAt(getCurrentDateTime());
        accountDto.setUpdatedAt(getCurrentDateTime());
        Account account = toEntity(accountDto);
        Account accountResult = repository.save(account);
        return  toDto(accountResult);
    }
}
