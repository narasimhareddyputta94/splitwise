package com.example.splitwise.controller;

import com.example.splitwise.model.Balance;
import com.example.splitwise.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/balances")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @PostMapping
    public Balance createBalance(@RequestBody Balance balance) {
        return balanceService.createBalance(balance);
    }

    @GetMapping
    public List<Balance> getAllBalances() {
        return balanceService.getAllBalances();
    }

    @GetMapping("/{balanceId}")
    public Optional<Balance> getBalanceById(@PathVariable Long balanceId) {
        return balanceService.getBalanceById(balanceId);
    }

    @GetMapping("/user/{userId}")
    public List<Balance> getBalancesByUserId(@PathVariable Long userId) {
        return balanceService.getBalancesByUserId(userId);
    }

    @PutMapping("/{balanceId}")
    public Balance updateBalance(@PathVariable Long balanceId, @RequestBody Balance balanceDetails) {
        return balanceService.updateBalance(balanceId, balanceDetails);
    }

    @DeleteMapping("/{balanceId}")
    public void deleteBalance(@PathVariable Long balanceId) {
        balanceService.deleteBalance(balanceId);
    }
}
