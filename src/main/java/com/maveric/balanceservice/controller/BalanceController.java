package com.maveric.balanceservice.controller;

import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "accounts")
public class BalanceController {
    @Autowired
    BalanceService balanceService;
    @GetMapping("/{accountId}/balances")
    public ResponseEntity<List<Balance>> getBalances(@PathVariable("accountId") String accountId){
        List<Balance> balances=balanceService.getBalances(accountId);
        return new ResponseEntity<>(balances, HttpStatus.OK);
    }





}
