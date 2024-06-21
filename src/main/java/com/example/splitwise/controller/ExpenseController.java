package com.example.splitwise.controller;

import com.example.splitwise.model.Expense;
import com.example.splitwise.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public Expense createExpense(@RequestBody Expense expense) {
        return expenseService.createExpense(expense);
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{expenseId}")
    public Optional<Expense> getExpenseById(@PathVariable Long expenseId) {
        return expenseService.getExpenseById(expenseId);
    }

    @GetMapping("/group/{groupId}")
    public List<Expense> getExpensesByGroupId(@PathVariable Long groupId) {
        return expenseService.getExpensesByGroupId(groupId);
    }

    @GetMapping("/user/{userId}")
    public List<Expense> getExpensesByUserId(@PathVariable Long userId) {
        return expenseService.getExpensesByUserId(userId);
    }

    @PutMapping("/{expenseId}")
    public Expense updateExpense(@PathVariable Long expenseId, @RequestBody Expense expenseDetails) {
        return expenseService.updateExpense(expenseId, expenseDetails);
    }

    @DeleteMapping("/{expenseId}")
    public void deleteExpense(@PathVariable Long expenseId) {
        expenseService.deleteExpense(expenseId);
    }
}
