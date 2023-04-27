package com.assignment.bluebik.service;

import java.math.BigDecimal;

import com.assignment.bluebik.entity.Account;

public interface AccountService {
	
	Account getAccount(Long accountId);
    BigDecimal getBalance(Long accountId);
    void updateBalance(Long accountId, BigDecimal amount);
}
