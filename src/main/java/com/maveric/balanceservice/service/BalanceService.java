package com.maveric.balanceservice.service;

import com.maveric.balanceservice.entity.Balance;



import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.exception.AccounIdMismatchException;
import com.maveric.balanceservice.exception.NoBalanceFoundException;
import com.maveric.balanceservice.exception.NoBalancesException;
import org.springframework.data.domain.Page;

public interface BalanceService {
    public Page<Balance> getBalances(String accountId, int page, int pageSize) throws NoBalancesException;
    public Balance updateBalance(String balanceId,String accountId, BalanceDto balanceDto) throws NoBalanceFoundException;
    public Balance createBalance(String accountId, BalanceDto balanceDto);
    public String deleteBalance(String accountId,String balanceId) throws NoBalanceFoundException, AccounIdMismatchException;

    public Balance getBalancesByBalanceId(String accountId,String balanceId ) throws NoBalanceFoundException;


}
