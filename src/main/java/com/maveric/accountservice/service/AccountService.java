package com.maveric.accountservice.service;

import com.maveric.accountservice.dto.AccountDto;

import java.util.List;

public interface AccountService {

    public AccountDto createAccount(AccountDto transaction);
    public AccountDto updateAccountDetails(String accountId,AccountDto accountDto);
    public String deleteAccount(String accountId);
    public List<AccountDto> getAccounts();
    public AccountDto getAccountDetailsById(String accountId);
}
