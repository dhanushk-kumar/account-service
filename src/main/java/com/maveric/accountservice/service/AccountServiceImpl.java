package com.maveric.accountservice.service;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.exception.AccountNotFoundException;
import com.maveric.accountservice.model.Account;
import com.maveric.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public AccountDto updateAccountDetails(String accountId, AccountDto accountDto) {
        Account accountResult=repository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        accountResult.set_id(accountResult.get_id());
        accountResult.setCustomerId(accountDto.getCustomerId());
        accountResult.setType(accountDto.getType());
        accountResult.setCreatedAt(accountResult.getCreatedAt());
        accountResult.setUpdatedAt(getCurrentDateTime());
        Account accountUpdated = repository.save(accountResult);
        return toDto(accountUpdated);
    }

    @Override
    public List<AccountDto> getAccounts() {
        List<Account> list= repository.findAll();
        List<AccountDto> listDto = new ArrayList<AccountDto>(list.size());
        for(Account account:list)
        {
            listDto.add(toDto(account));
        }
        return listDto;
    }

    @Override
    public String deleteAccount(String accountId) {
        repository.deleteById(accountId);
        return "Account deleted successfully.";
    }
}
