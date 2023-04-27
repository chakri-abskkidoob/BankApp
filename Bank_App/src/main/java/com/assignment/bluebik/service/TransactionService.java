package com.assignment.bluebik.service;

import java.math.BigDecimal;

public interface TransactionService {
	
	void credit(Long userId, Long accountId, BigDecimal amount);
    void debit(Long userId, Long accountId, BigDecimal amount);
}
