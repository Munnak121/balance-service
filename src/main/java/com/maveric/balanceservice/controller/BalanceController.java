package com.maveric.balanceservice.controller;

import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.exception.AccounIdMismatchException;
import com.maveric.balanceservice.exception.NoBalanceFoundException;
import com.maveric.balanceservice.exception.NoBalancesException;
import com.maveric.balanceservice.service.BalanceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.maveric.balanceservice.dto.BalanceDto;

import org.springframework.data.domain.Page;
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
    @GetMapping(path = "/{accountId}/balances", params = {"page","pageSize"})
    public ResponseEntity<List<BalanceDto>> getBalances(@PathVariable("accountId") String accountId,@RequestParam(name = "page" ,defaultValue = "1") int page,@RequestParam(name = "pageSize" ,defaultValue = "10")int pageSize) throws NoBalancesException {
        Page<Balance> balances = balanceService.getBalances(accountId, page,pageSize);
        List<BalanceDto> dtos = balances
                .stream()
                .map(user -> modelMapper.map(user, BalanceDto.class))
                .toList();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


    @DeleteMapping("/{accountId}/balances/{balanceId}")
    public ResponseEntity<String> deleteBalance(@PathVariable("accountId") String accountId, @PathVariable("balanceId") String balanceId) {
        String message = balanceService.deleteBalance(accountId, balanceId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @PostMapping("/{id}/balances")
    public ResponseEntity<Balance> createBalance(@PathVariable(name = "id") String accountId, @RequestBody @Valid BalanceDto balanceDto) throws AccounIdMismatchException {
        if(!accountId.equals(balanceDto.getAccountId())){
            throw new AccounIdMismatchException("Account ID in url is diff from Account Id in the RequestBody ");
        }
        Balance balance1 = balanceService.createBalance(accountId, balanceDto);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("message", "Balance Created Successfully");
        return new ResponseEntity<>(balance1, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{accountId}/balances/{balanceId}")
    public ResponseEntity<Balance> updateBalance(@PathVariable(name = "balanceId") String balanceId, @PathVariable(name = "accountId") String accountId, @RequestBody @Valid BalanceDto balanceDto) throws NoBalanceFoundException {

        Balance updatedBalance = balanceService.updateBalance(balanceId, accountId, balanceDto);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("message",
                "Balance successfully updated");
        return new ResponseEntity<>(updatedBalance, responseHeaders, HttpStatus.OK);
    }

    @GetMapping(path = "/{accountId}/balances/{balanceId}")
    public ResponseEntity<BalanceDto> getBalanceByID(@PathVariable("accountId") String accountId,@PathVariable("balanceId") String balanceId) throws  NoBalanceFoundException {
        Balance balance = balanceService.getBalancesByBalanceId(accountId,balanceId);
       BalanceDto responseDto=modelMapper.map(balance, BalanceDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
