package kh.com.sbilhbank.nbc.controller;

import kh.com.sbilhbank.nbc.model.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class AccountController {

    private Map<String, Account> accounts = new HashMap<>();

    public AccountController() {
        // Sample data for demonstration
        accounts.put("1", new Account("1", "Liza BlackPink", 1000.0));
        accounts.put("2", new Account("2", "Jiso BlackPink", 2000.0));
        accounts.put("3", new Account("3", "Rose BlackPink", 1500.0));
    }

    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable String id) {
        return accounts.get(id);
    }
}
