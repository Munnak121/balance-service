package com.maveric.balanceservice.service;

import com.maveric.balanceservice.entity.Balance;

import java.util.List;


public interface BalanceService {
    public List<Balance> getBalances(String accountId);

}
