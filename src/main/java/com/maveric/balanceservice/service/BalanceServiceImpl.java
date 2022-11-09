package com.maveric.balanceservice.service;

import com.maveric.balanceservice.Dto.BalanceDto;
import com.maveric.balanceservice.Entity.Balance;
import com.maveric.balanceservice.Repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalanceServiceImpl implements BalanceService{

    @Autowired
    BalanceRepository balanceRepository;


    @Override
    public Balance updateBalance(String balanceId, String accountId ,BalanceDto balanceDto) {
     Balance balance= balanceRepository.findById(balanceId).get();
     Integer existingBalance=  balance.getAmount();
     if(existingBalance==null){
         existingBalance=0;
     }
     balance.setAmount(existingBalance+balanceDto.getAmount());
     balance.setCurrency(balanceDto.getCurrency());
        balanceRepository.save(balance);
        return  balance;

    }
}
