package com.maveric.balanceservice.controller;

import com.maveric.balanceservice.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "accounts")
public class BalanceController {
    @Autowired
    BalanceService balanceService;


@GetMapping("/{accountId}/balances/{balanceId}")
    public ResponseEntity getBalanceByBalanceId(@PathVariable("accountId") String accountId,@PathVariable("balanceId") String balanceId){
   Integer count= balanceService.getBalanceDetails(accountId,balanceId);
    return new ResponseEntity<>(count, HttpStatus.OK);
}


}
