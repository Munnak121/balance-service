package com.maveric.balanceservice.service;

import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceServiceImpl implements BalanceService{

    @Autowired
    BalanceRepository balanceRepository;

    @Override
    public Balance createBalance(String accountId, Balance balance) {

        Balance b2=balanceRepository.save(balance);
        return b2;
    }

}
