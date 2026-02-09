package com.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.entity.Account;
import com.bank.entity.Transaction;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;

@Service
public class TransactionService {

    @Value("${banking.alert.threshold}")
    private double threshold;

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(AccountRepository accountRepository,
                              TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    // ---------------------------
    // Deposit
    // ---------------------------
    @Transactional
    public Transaction deposit(Long accountId, Double amount) {

        Account acc = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        acc.setBalance(acc.getBalance() + amount);
        accountRepository.save(acc);

        return transactionRepository.save(
                new Transaction(acc, "DEPOSIT", amount, "Amount deposited")
        );
    }

      

    // ---------------------------
    // Withdraw
    // ---------------------------
    @Transactional
    public Transaction withdraw(Long accountId, Double amount) {

        Account acc = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (acc.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        acc.setBalance(acc.getBalance() - amount);
        accountRepository.save(acc);

        return transactionRepository.save(
                new Transaction(acc, "WITHDRAW", amount, "Amount withdrawn")
        );
    }

    // ---------------------------
    // Transfer
    // ---------------------------
    @Transactional
    public String transfer(Long fromAccountId, Long toAccountId, Double amount) {

        Account from = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Sender account not found"));

        Account to = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("Receiver account not found"));

        if (from.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        accountRepository.save(from);
        accountRepository.save(to);

        transactionRepository.save(
                new Transaction(from, "TRANSFER", amount, "Sent to " + toAccountId)
        );

        transactionRepository.save(
                new Transaction(to, "TRANSFER", amount, "Received from " + fromAccountId)
        );

        return "Transfer successful";
    }

    // ---------------------------
    // Transaction History
    // ---------------------------
    public List<Transaction> getTransactionsByAccount(Long accountId) {
        return transactionRepository
                .findByAccountIdOrderByCreatedAtDesc(accountId);
    }
}