package kh.com.sbilhbank.cbs.controller;


import kh.com.sbilhbank.cbs.model.BankAccount;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/bank-accounts")
public class cbs {

    private final List<BankAccount> bankAccounts = new ArrayList<>();

    // Pre-populate some fixed accounts for testing
    {
        BankAccount account1 = new BankAccount("123456", "Account Holder 1", 1000.0);
        BankAccount account2 = new BankAccount("789012", "Account Holder 2", 500.0);

        bankAccounts.add(account1);
        bankAccounts.add(account2);
    }

    @Operation(summary = "Get account details by account number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @GetMapping("/{accountNumber}")
    public ResponseEntity<BankAccount> getAccount(@PathVariable String accountNumber) {
        if (accountNumber == null) {
            return ResponseEntity.badRequest().build();
        }

        BankAccount account = bankAccounts.stream()
                .filter(a -> accountNumber.equals(a.getAccountNumber()))
                .findFirst()
                .orElse(null);

        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Create a new bank account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping
    public ResponseEntity<Void> createAccount(@RequestBody BankAccount bankAccount) {
        if (bankAccount.getAccountNumber() == null || bankAccount.getAccountNumber().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Check if the account number already exists
        if (bankAccounts.stream().anyMatch(a -> bankAccount.getAccountNumber().equals(a.getAccountNumber()))) {
            return ResponseEntity.badRequest().build();
        }

        bankAccounts.add(bankAccount);
        return ResponseEntity.created(null).build();
    }

    @Operation(summary = "Update account balance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Balance updated successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @PutMapping("/{accountNumber}/balance")
    public ResponseEntity<Void> updateBalance(@PathVariable String accountNumber, @RequestParam double newBalance) {
        if (accountNumber == null) {
            return ResponseEntity.badRequest().build();
        }

        BankAccount account = bankAccounts.stream()
                .filter(a -> accountNumber.equals(a.getAccountNumber()))
                .findFirst()
                .orElse(null);

        if (account != null) {
            account.setBalance(newBalance);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

