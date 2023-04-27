package com.assignment.bluebik.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.bluebik.service.TransactionService;


@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/credit")
    public void credit(@RequestParam Long userId, @RequestParam Long accountId, @RequestParam BigDecimal amount) {
        transactionService.credit(userId, accountId, amount);
    }

    @PostMapping("/debit")
    public void debit(@RequestParam Long userId, @RequestParam Long accountId, @RequestParam BigDecimal amount) {
        transactionService.debit(userId, accountId, amount);
    }
    
}

