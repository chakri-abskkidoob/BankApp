package com.assignment.bluebik.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.bluebik.entity.Account;
import com.assignment.bluebik.entity.Transaction;
import com.assignment.bluebik.entity.User;
import com.assignment.bluebik.repository.AccountRepository;
import com.assignment.bluebik.repository.TransactionRepository;
import com.assignment.bluebik.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void credit(Long userId, Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if (account != null && user != null) {
            BigDecimal newBalance = account.getBalance().add(amount);
//            if (newBalance.compareTo(BigDecimal.TEN_) > 0) {
//                throw new RuntimeException("Account balance cannot go beyond 10 million");
//            }
            account.setBalance(newBalance);
            accountRepository.save(account);

            Transaction transaction = new Transaction();
            transaction.setType("credit");
            transaction.setAmount(amount);
            transaction.setTimestamp(LocalDateTime.now());
            transaction.setUser(user);
            transaction.setAccount(account);
            transactionRepository.save(transaction);
        }
    }

    @Override
    public void debit(Long userId, Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if (account != null && user != null) {
            BigDecimal newBalance = account.getBalance().subtract(amount);
            if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
                throw new RuntimeException("Account balance cannot go below 0");
            }
            account.setBalance(newBalance);
            accountRepository.save(account);

            Transaction transaction = new Transaction();
            transaction.setType("debit");
            transaction.setAmount(amount);
            transaction.setTimestamp(LocalDateTime.now());
            transaction.setUser(user);
            transaction.setAccount(account);
            transactionRepository.save(transaction);
        }
    }
}
