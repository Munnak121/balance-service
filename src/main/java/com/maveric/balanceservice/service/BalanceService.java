package com.maveric.balanceservice.service;

import com.maveric.balanceservice.Dto.BalanceDto;
import com.maveric.balanceservice.Entity.Balance;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BalanceService {
    public List<Balance> ListBalance(String accountId);
    public Balance createBalance(String accountId, Balance balance);



    public Balance getBalance(String balanceId, String accountId);
    public Balance updateBalance(String balanceId,String accountId);
    public String deleteBalance(String balanceId,String accountId);


    public Balance getBalanceDetails();
}
