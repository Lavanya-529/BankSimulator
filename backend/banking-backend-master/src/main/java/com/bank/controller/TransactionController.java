package com.bank.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.bank.dto.DepositRequest;
import com.bank.entity.Transaction;
import com.bank.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public Transaction deposit(@RequestBody DepositRequest request) {
        return transactionService.deposit(
                request.getAccountId(),
                request.getAmount()
        );
    }

    // ---------------------------
    // Withdraw
    // ---------------------------
    @PostMapping("/withdraw")
    public Transaction withdraw(@RequestParam Long accountId,
                                @RequestParam Double amount) {
        return transactionService.withdraw(accountId, amount);
    }

    // ---------------------------
    // Transfer
    // ---------------------------
    @PostMapping("/transfer")
    public String transfer(@RequestParam Long fromAccountId,
                           @RequestParam Long toAccountId,
                           @RequestParam Double amount) {
        return transactionService.transfer(fromAccountId, toAccountId, amount);
    }

    // ---------------------------
    // Transaction History
    // ---------------------------
    @GetMapping("/history/{accountId}")
    public List<Transaction> getHistory(@PathVariable Long accountId) {
        return transactionService.getTransactionsByAccount(accountId);
    }
}