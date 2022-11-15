package com.maveric.balanceservice.service;

import com.maveric.balanceservice.entity.Balance;

import java.util.List;

import com.maveric.balanceservice.dto.BalanceDto;
public interface BalanceService {


    public Integer getBalanceDetails(String accounId,String balanceId);
  public List<Balance> getBalances(String accountId);
    public Balance updateBalance(String balanceId,String accountId, BalanceDto balanceDto);
    public Balance createBalance(String accountId, Balance balance);
    public String deleteBalance(String accountId,String balanceId);

}
