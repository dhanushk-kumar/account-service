package com.maveric.accountservice.service;

import com.maveric.accountservice.constants.Type;
import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.model.Account;
import com.maveric.accountservice.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImpTest {
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void shouldReturnAccountWhenCreateAccountInvoked() {

        when(accountRepository.save(any())).thenReturn(getAccount());

        AccountDto account = accountService.createAccount(getAccountDto());

        assertNotNull(account);
        assertSame(account.getType(),getAccount().getType());
    }

    @Test
    void ShouldReturnAccountDetailswhenAccountByIdInvoked() {
        when(accountRepository.findById(any(String.class))).thenReturn(Optional.of(getAccount()));

        AccountDto account = accountService.getAccountDetailsById("123");

        assertNotNull(account);
        assertSame(account.getType(), getAccount().getType());
    }

    @Test
    void shouldDeleteAccountwhenDeleteAccountInvoked(){

        accountRepository.deleteById("123");
        verify(accountRepository,atLeastOnce()).deleteById("123");
    }

    @Test
    void shouldReturnTransactionswhenAccountsnotemptyindb(){
        List<Account> accounts = new ArrayList<Account>();
        accounts.add(getAccount());
        when(accountRepository.findAll()).thenReturn(accounts);
        assertFalse(accountService.getAccounts().isEmpty());

    }

    private Account getAccount() {
        Account account = new Account();
        account.set_id("1");
        account.setCustomerId("12");
        account.setType(Type.SAVINGS);
        account.setCreatedAt(LocalDateTime.parse("1986-04-08 12:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        account.setUpdatedAt(LocalDateTime.now());
        return account;
    }

    private AccountDto getAccountDto() {
        AccountDto accountdto = new AccountDto();
        accountdto.set_id("1");
        accountdto.setCustomerId("12");
        accountdto.setType(Type.SAVINGS);
        accountdto.setCreatedAt(LocalDateTime.parse("1986-04-08 12:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        accountdto.setUpdatedAt(LocalDateTime.now());
        return accountdto;
    }
}
