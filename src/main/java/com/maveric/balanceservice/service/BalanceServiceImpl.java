package com.maveric.balanceservice.service;

import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.repository.BalanceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
