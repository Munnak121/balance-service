package com.maveric.balanceservice.service;

import com.maveric.balanceservice.Dto.BalanceDto;
import com.maveric.balanceservice.Entity.Balance;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BalanceService {

    public String deleteBalance(String accountId,String balanceId);

}
