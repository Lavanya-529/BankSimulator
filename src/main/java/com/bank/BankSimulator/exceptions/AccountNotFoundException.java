package com.bank.BankSimulator.exceptions;


public class AccountNotFoundException extends Exception {

    public AccountNotFoundException(String message) {
        super(message);
    }
}