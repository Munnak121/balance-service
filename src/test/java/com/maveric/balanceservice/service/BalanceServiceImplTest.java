package com.maveric.balanceservice.service;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.enums.Currency;
import com.maveric.balanceservice.exception.NoBalanceFoundException;
import com.maveric.balanceservice.exception.NoBalancesException;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
@ExtendWith(MockitoExtension.class)
class BalanceServiceImplTest {
    @Mock
    BalanceRepository repository;
    @InjectMocks
    BalanceServiceImpl service;
    @Mock
    ModelMapper modelMapper;

    public static BalanceDto getBalanceDto(){
        BalanceDto balanceDto=new BalanceDto();
        balanceDto.setAmount(100);
        balanceDto.setCurrency(Currency.INR);
        balanceDto.setAccountId("130");
        balanceDto.setId("1");
        return balanceDto;
    }

    public static Balance getBalance(){
        Balance balance=new Balance();
        balance.setAmount(100);
        balance.setCurrency(Currency.INR);
        balance.setAccountId("130");
        balance.setId("1");
        return balance;
    }

    public static Page<Balance> getBalances(){
        List<Balance> list=new ArrayList<>();
        list.add(getBalance());
        list.add(getBalance());
        list.add(getBalance());
        list.add(getBalance());
        Page<Balance> pages = new PageImpl<Balance>(list);
        return pages;

    }


    @Test
    void should_getpageable_Balances_when_getBalances_invoked_test() throws NoBalancesException {
        when(repository.findAllByAccountId("130")).thenReturn(getBalances().stream().toList());
        when(repository.findAllByAccountId("130",PageRequest.of(0,10))).thenReturn(getBalances());

        Page<Balance> balances=service.getBalances("130",0,10);

        assertNotNull(balances);
        assertEquals(4,balances.getSize());

    }

    @Test
    void shoudlupdateBalance_whenInvoked() throws NoBalanceFoundException {
        when(repository.findById(any(String.class))).thenReturn(Optional.of(getBalance()));
        when(repository.save(any(Balance.class))).thenReturn(getBalance());

        Balance balance=service.updateBalance("1","1",getBalanceDto());
      //  assertEquals(getBalance(),balance);
        assertEquals(getBalance().getId(),balance.getId());

    }

    @Test
    void deleteBalance() {
    }

    @Test
    void createBalance() {
    }

    @Test
    void getBalancesByBalanceId() {
    }

    @Test
    void testGetBalances() {
    }


    @Test
    void testDeleteBalance() {
    }

    @Test
    void testCreateBalance() {
    }

    @Test
    void testGetBalancesByBalanceId() {
    }
}