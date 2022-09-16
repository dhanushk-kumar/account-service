package com.maveric.accountservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.accountservice.constants.Type;
import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.repository.AccountRepository;
import com.maveric.accountservice.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    ObjectMapper map;
    @MockBean
    private AccountService mockService;
    @MockBean
    private AccountRepository mockRepo;

    @Test
    void ShouldCreateAccountWhenPostAccountIsInvoked() throws Exception {
        AccountDto accountDto = new AccountDto();
        accountDto.set_id("1");
        accountDto.setCustomerId("12");
        accountDto.setType(Type.SAVINGS);
        accountDto.setCreatedAt(LocalDateTime.parse("1986-04-08 12:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        accountDto.setUpdatedAt(LocalDateTime.now());

        mvc.perform(post("/api/v1/customer/" + "12" + "/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(map.writeValueAsBytes(accountDto)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void shouldGetAccountWhenRequestMadeToGetAccounts() throws Exception {
        mvc.perform(get("/api/v1/customer/"+"12"+"/accounts").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void shouldGetAccountDetailsWhenGetAccountByIdInvoked() throws Exception {
        AccountDto accountDto = new AccountDto();
        accountDto.set_id("1");
        accountDto.setCustomerId("12");
        accountDto.setType(Type.SAVINGS);
        accountDto.setCreatedAt(LocalDateTime.parse("1986-04-08 12:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        accountDto.setUpdatedAt(LocalDateTime.now());

        String url = "/api/v1/customer/" + "12" + "/accounts/" + "1";
        mvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void ShouldDeleteAccountWhenDeleteAccountByIdRequestIsMade() throws Exception {

        mvc.perform(delete("/api/v1/customer/" + "13" + "/accounts/" + "1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void ShouldUpdateAccountWhenPutAccountIsInvoked() throws Exception {
        AccountDto accountDto = new AccountDto();
        accountDto.set_id("1");
        accountDto.setCustomerId("13");
        accountDto.setType(Type.SAVINGS);
        accountDto.setCreatedAt(LocalDateTime.parse("1986-04-08 12:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        accountDto.setUpdatedAt(LocalDateTime.now());

        mvc.perform(put("/api/v1/customer/" + "13" + "/accounts/"+"1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(map.writeValueAsBytes(accountDto)))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
