package com.maveric.balanceservice.controller;

import com.maveric.balanceservice.Entity.Balance;
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
//
//  @DeleteMapping("/{accountId}/balances/{balanceId}") //@PathVariable(name = "accountId") @PathVariable(name = "balanceId")
//    public ResponseEntity<String> (String balanceID, String accountId){
//
//        return new ResponseEntity<>("null");
//    }
 @DeleteMapping("/{accountId}/balances/{balanceId}")
  public ResponseEntity deleteBalance(@PathVariable("accountId") String accountId,@PathVariable("balanceId") String balanceId){
        String message=balanceService.deleteBalance(accountId,balanceId);
      return new ResponseEntity(message, HttpStatus.OK);
  }

}
