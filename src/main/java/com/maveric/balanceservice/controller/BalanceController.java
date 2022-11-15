package com.maveric.balanceservice.controller;

import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "accounts")
public class BalanceController {
    @Autowired
    BalanceService balanceService;


    @PostMapping("/{id}/balances")
    public ResponseEntity <Balance> createBalance(@PathVariable(name = "id") String accountId, @RequestBody Balance balance){
        Balance balance1= balanceService.createBalance(accountId,balance);
        HttpHeaders responseHeaders=new HttpHeaders();
         responseHeaders.add("message","Balance Created Successfully");
        return new ResponseEntity(balance1,responseHeaders, HttpStatus.CREATED);
    }



}
