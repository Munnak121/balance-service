package com.maveric.balanceservice.service;

import com.maveric.balanceservice.entity.Balance;

import java.util.List;


public interface BalanceService {

    public Balance createBalance(String accountId, Balance balance);


}
