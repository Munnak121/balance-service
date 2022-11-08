package com.maveric.balanceservice.controller;

import com.maveric.balanceservice.Entity.Balance;
import com.maveric.balanceservice.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "accounts")
public class BalanceController {
    @Autowired
    BalanceService balanceService;





}
