package com.maveric.balanceservice.service;


import com.maveric.balanceservice.dto.BalanceDto;

import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.exception.NoBalanceFoundException;
import com.maveric.balanceservice.exception.NoBalancesException;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BalanceServiceImpl implements BalanceService{

    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public Page<Balance> getBalances(String accountId,int page,int pageSize) throws NoBalancesException {


       Page<Balance> balances=balanceRepository.findAllByAccountId(accountId,PageRequest.of(page,pageSize));
       // List<Balance> balances=
        if (balances.isEmpty()) {
            throw new NoBalancesException("No Balances for AccountId, Please Create !! "+accountId);
        }
        return balances;
    }

    @Override
    public Balance updateBalance(String balanceId, String accountId ,BalanceDto balanceDto) throws NoBalanceFoundException {
     Optional<Balance> balanceOptional= balanceRepository.findById(balanceId);
     if (!balanceOptional.isPresent()){
         throw new NoBalanceFoundException("No balance found to update for given balance id");
     }
     Balance balance=balanceOptional.get();
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
    public Balance createBalance(String accountId, BalanceDto balanceDto) {
        Balance balance=modelMapper.map(balanceDto, Balance.class);
        Balance b2=balanceRepository.save(balance);
        return b2;
    }

    @Override
    public Balance getBalancesByBalanceId(String accountId, String balanceId) throws NoBalanceFoundException {
        Optional<Balance> balance=balanceRepository.findById(balanceId);
        // List<Balance> balances=
        if (balance.isEmpty()) {
            throw new NoBalanceFoundException("No Balances for given balance id"+balanceId);
        }
        return balance.get();
    }
}
