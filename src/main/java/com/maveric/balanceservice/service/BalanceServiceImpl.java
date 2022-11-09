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
    public String deleteBalance(String accountId, String balanceId ) {
        Balance balance=balanceRepository.findById(balanceId).get();
        balanceRepository.delete(balance);
        return "Balance deleted Successfully";
    }
}
