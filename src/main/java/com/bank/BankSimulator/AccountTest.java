package com.bank.BankSimulator;

import java.math.BigDecimal;
import java.util.Collection;

import com.bank.BankSimulator.exceptions.AccountNotFoundException;
import com.bank.BankSimulator.exceptions.InvalidAmountException;
import com.bank.BankSimulator.model.Account;
import com.bank.BankSimulator.repository.AccountRepository;
import com.bank.BankSimulator.service.AccountService;

public class AccountTest {

    public static void main(String[] args) {

        // Create repository and service
        AccountRepository repo = new AccountRepository();
        AccountService service = new AccountService(repo);

        try {
            // Create accounts
            Account acc1 = service.createAccount("Krish", "lavanyageesala2110@gmail.com", new BigDecimal("2000"));
            Account acc2 = service.createAccount("Radha", "radha@gmail.com", new BigDecimal("5000"));

            System.out.println("Created Accounts:");
            System.out.println(acc1);
            System.out.println(acc2);

            System.out.println("--------------------------------");

            // Get account using VALID account number
            String accNumber = acc1.getAccountNumber();
            Account fetchedAccount = service.getAccount(accNumber);

            System.out.println("Getting account based on account number:");
            System.out.println(fetchedAccount);

            System.out.println("--------------------------------");
            System.out.println("Collecting all accounts:");

            // List all accounts
            Collection<Account> allAccounts = service.listAll();
            for (Account a : allAccounts) {
                System.out.println(a);
            }

        } catch (InvalidAmountException | AccountNotFoundException e) {
            e.printStackTrace();
        }

        
    }
}