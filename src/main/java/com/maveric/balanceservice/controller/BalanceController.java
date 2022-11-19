package com.maveric.balanceservice.controller;

import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.exception.AccounIdMismatchException;
import com.maveric.balanceservice.exception.NoBalancesException;
import com.maveric.balanceservice.service.BalanceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.maveric.balanceservice.dto.BalanceDto;

import org.springframework.http.HttpHeaders;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/accounts")
public class BalanceController {
    @Autowired
    BalanceService balanceService;

    @Autowired
    ModelMapper modelMapper;
    @GetMapping("/{accountId}/balances")
    public ResponseEntity<List<BalanceDto>> getBalances(@PathVariable("accountId") String accountId) throws NoBalancesException {
        List<Balance> balances = balanceService.getBalances(accountId);
        List<BalanceDto> dtos = balances
                .stream()
                .map(user -> modelMapper.map(user, BalanceDto.class))
                .toList();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    //
//  @DeleteMapping("/{accountId}/balances/{balanceId}") //@PathVariable(name = "accountId") @PathVariable(name = "balanceId")
//    public ResponseEntity<String> (String balanceID, String accountId){
//
//        return new ResponseEntity<>("null");
//    }
    @DeleteMapping("/{accountId}/balances/{balanceId}")
    public ResponseEntity deleteBalance(@PathVariable("accountId") String accountId, @PathVariable("balanceId") String balanceId) {
        String message = balanceService.deleteBalance(accountId, balanceId);
        return new ResponseEntity(message, HttpStatus.OK);
    }


    @PostMapping("/{id}/balances")
    public ResponseEntity<Balance> createBalance(@PathVariable(name = "id") String accountId, @RequestBody @Valid BalanceDto balanceDto) throws AccounIdMismatchException {
        if(!accountId.equals(balanceDto.getAccountId())){
            throw new AccounIdMismatchException("Account ID in url is diff from Account Id in the RequestBody ");
        }
        Balance balance1 = balanceService.createBalance(accountId, balanceDto);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("message", "Balance Created Successfully");
        return new ResponseEntity(balance1, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{accountId}/balances/{balanceId}")
    public ResponseEntity<Balance> updateBalance(@PathVariable(name = "balanceId") String balanceId, @PathVariable(name = "accountId") String accountId, @RequestBody @Valid BalanceDto balanceDto) {

        Balance updatedBalance = balanceService.updateBalance(balanceId, accountId, balanceDto);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("message",
                "Balance successfully updated");
        return new ResponseEntity<>(updatedBalance, responseHeaders, HttpStatus.OK);
    }


}
