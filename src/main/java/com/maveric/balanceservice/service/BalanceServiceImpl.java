package com.maveric.balanceservice.service;

import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BalanceServiceImpl implements BalanceService{

    @Autowired
    BalanceRepository balanceRepository;



    @Override
    public Integer getBalanceDetails(String accountId, String balanceId) {
       List<Balance> balanceList= balanceRepository.findById(balanceId).stream().collect(Collectors.toList());

       return balanceList.size();
    }
}
