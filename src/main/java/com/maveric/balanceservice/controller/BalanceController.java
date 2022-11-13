package com.maveric.balanceservice.controller;

import com.maveric.balanceservice.dto.BalanceDto;
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


    @PutMapping("/{accountId}/balances/{balanceId}")
public ResponseEntity <Balance> updateBalance(@PathVariable(name = "balanceId") String balanceId, @PathVariable(name = "accountId") String accountId, @RequestBody BalanceDto balanceDto){

   Balance updatedBalance= balanceService.updateBalance(balanceId,accountId,balanceDto);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("message",
                "Balance successfully updated");
        return new ResponseEntity<>(updatedBalance,responseHeaders, HttpStatus.OK);
}


}
