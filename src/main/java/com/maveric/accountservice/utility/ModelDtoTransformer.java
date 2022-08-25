package com.maveric.accountservice.utility;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.model.Account;

public class ModelDtoTransformer {
    public static Account toEntity(AccountDto dto) {
        return new Account(
                dto.get_id(),
                dto.getCustomerId(),
                dto.getType(),
                dto.getUpdatedAt(),
                dto.getCreatedAt()
        );
    }

    public static AccountDto toDto(Account model) {
        return new AccountDto(
                model.get_id(),
                model.getCustomerId(),
                model.getType(),
                model.getUpdatedAt(),
                model.getCreatedAt()
        );
    }
}
