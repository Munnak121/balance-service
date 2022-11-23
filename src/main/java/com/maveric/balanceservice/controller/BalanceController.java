package com.maveric.balanceservice.controller;

import com.maveric.balanceservice.entity.Balance;
import com.maveric.balanceservice.exception.AccounIdMismatchException;
import com.maveric.balanceservice.exception.AmountisNegative;
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


    /**
     * Get all balanced for particular account
     * Returns a list of balances for a particular account.
     * uri: localhost:3015/accounts/{accountId}/balances
     * @param accountId
     * @param page
     * @param pageSize
     * @return
     * @throws NoBalancesException
     */

    @GetMapping(path = "/{accountId}/balances", params = {"page","pageSize"})
    public ResponseEntity<List<BalanceDto>> getBalances(@PathVariable("accountId") String accountId,@RequestParam(name = "page" ,defaultValue = "1") int page,@RequestParam(name = "pageSize" ,defaultValue = "10")int pageSize) throws NoBalancesException {
        Page<Balance> balances = balanceService.getBalances(accountId, page,pageSize);
        List<BalanceDto> dtos = balances
                .stream()
                .map(balance -> modelMapper.map(balance, BalanceDto.class))
                .toList();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    /**
     * Delete Balance
     * Uri:localhost:3015/accounts/{accountId}/balances/{balanceId}
     * @param accountId
     * @param balanceId
     * @return
     * @throws NoBalanceFoundException
     * @throws AccounIdMismatchException
     */
    @DeleteMapping("/{accountId}/balances/{balanceId}")
    public ResponseEntity<String> deleteBalance(@PathVariable("accountId") String accountId, @PathVariable("balanceId") String balanceId) throws NoBalanceFoundException, AccounIdMismatchException {

        String message = balanceService.deleteBalance(accountId, balanceId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * Create Balance
     * uri:localhost:3015/accounts/{accountId}/balances
     * @param accountId
     * @param balanceDto
     * @return
     * @throws AccounIdMismatchException
     * @throws AmountisNegative
     * @RequestBody
     * {
     *   "amount": "string",
     *   "currency": "INR",
     *   "accountId": "string"
     * }
     */

    @PostMapping("/{id}/balances")
    public ResponseEntity<Balance> createBalance(@PathVariable(name = "id") String accountId, @RequestBody @Valid BalanceDto balanceDto) throws AccounIdMismatchException, AmountisNegative {
        if(!accountId.equals(balanceDto.getAccountId())){
            throw new AccounIdMismatchException("Account ID in url is diff from Account Id in the RequestBody ");
        }
        if(balanceDto.getAmount()<0){
            throw new AmountisNegative("Amount cannot be negative");
        }
        Balance balance1 = balanceService.createBalance(accountId, balanceDto);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("message", "Balance Created Successfully");
        return new ResponseEntity<>(balance1, responseHeaders, HttpStatus.CREATED);
    }

    /**
     * Update Balance
     * Uri:localhost:3015/accounts/{accountId}/balances/{balanceId}
     * @param balanceId
     * @param accountId
     * @param balanceDto
     * @return
     * @throws NoBalanceFoundException
     * @throws AccounIdMismatchException
     * @throws AmountisNegative
     * RequestBody
     * {
     *   "amount": "string",
     *   "currency": "INR",
     *   "accountId": "string"
     * }
     */
    @PutMapping("/{accountId}/balances/{balanceId}")
    public ResponseEntity<Balance> updateBalance(@PathVariable(name = "balanceId") String balanceId, @PathVariable(name = "accountId") String accountId, @RequestBody @Valid BalanceDto balanceDto) throws NoBalanceFoundException, AccounIdMismatchException, AmountisNegative {
        if(!accountId.equals(balanceDto.getAccountId())){
            throw new AccounIdMismatchException("Account ID in url is diff from Account Id in the RequestBody ");
        }
        if(balanceDto.getAmount()<0){
            throw new AmountisNegative("Amount cannot be negative");
        }
        Balance updatedBalance = balanceService.updateBalance(balanceId, accountId, balanceDto);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("message",
                "Balance successfully updated");
        return new ResponseEntity<>(updatedBalance, responseHeaders, HttpStatus.OK);
    }

    /**
     * Get Balance by given Balance Id
     * Uri:localhost:3015/accounts/{accountId}/balances/{balanceId}
     * @param accountId
     * @param balanceId
     * @return
     * @throws NoBalanceFoundException
     * @throws AccounIdMismatchException
     */
    @GetMapping(path = "/{accountId}/balances/{balanceId}")
    public ResponseEntity<BalanceDto> getBalanceByID(@PathVariable("accountId") String accountId,@PathVariable("balanceId") String balanceId) throws NoBalanceFoundException, AccounIdMismatchException {

        Balance balance = balanceService.getBalancesByBalanceId(accountId,balanceId);
        if(!accountId.equals(balance.getAccountId())){
            throw new AccounIdMismatchException("Account Id in url doesn't belong to this balance id");
        }
       BalanceDto responseDto=modelMapper.map(balance, BalanceDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
