package com.assignment.bluebik.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.bluebik.entity.Account;
import com.assignment.bluebik.repository.AccountRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account getAccount(Long accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

    @Override
    public BigDecimal getBalance(Long accountId) {
        Account account = getAccount(accountId);
        return account != null ? account.getBalance() : BigDecimal.ZERO;
    }

    @Override
    public void updateBalance(Long accountId, BigDecimal amount) {
        Account account = getAccount(accountId);
        if (account != null) {
            BigDecimal newBalance = account.getBalance().add(amount);
            if (newBalance.compareTo(BigDecimal.TEN) > 0) {
            	throw new IllegalArgumentException("Credit amount exceeds the limit of 10 million.");
            }
            if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Debit amount exceeds the available balance.");
            }
            account.setBalance(newBalance);
            accountRepository.save(account);
            }
            }
            }


