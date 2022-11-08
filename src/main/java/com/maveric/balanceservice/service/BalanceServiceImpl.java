package com.maveric.balanceservice.service;

import com.maveric.balanceservice.Dto.BalanceDto;
import com.maveric.balanceservice.Entity.Balance;
import com.maveric.balanceservice.Repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceServiceImpl implements BalanceService{

    @Autowired
    BalanceRepository balanceRepository;


    @Override
    public List<Balance> ListBalance(String accountId) {
        return null;
    }



    @Override
    public Balance createBalance(String accountId, Balance balance) {
        Balance b2=balanceRepository.save(balance);
        return b2;
    }



    @Override
    public Balance getBalance(String balanceId, String accountId) {
        return null;
    }

    @Override
    public Balance updateBalance(String balanceId, String accountId) {
        return null;
    }

    @Override
    public String deleteBalance(String balanceId, String accountId) {
        return null;
    }

    @Override
    public Balance getBalanceDetails() {
        return null;
    }
}
