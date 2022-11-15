package com.maveric.balanceservice.service;

import com.maveric.balanceservice.dto.BalanceDto;
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



    @Override
    public List<Balance> getBalances(String accountId) {
        return balanceRepository.findAllByAccountId(accountId);
    }

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
    @Override
    public String deleteBalance(String accountId, String balanceId ) {
        Balance balance=balanceRepository.findById(balanceId).get();
        balanceRepository.delete(balance);
        return "Balance deleted Successfully";
    }

    @Override
    public Balance createBalance(String accountId, Balance balance) {

        Balance b2=balanceRepository.save(balance);
        return b2;
    }


}
