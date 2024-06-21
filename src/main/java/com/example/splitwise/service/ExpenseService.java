package com.example.splitwise.service;

import com.example.splitwise.Repositories.ExpenseRepository;
import com.example.splitwise.Repositories.UserRepository;
import com.example.splitwise.model.Expense;
import com.example.splitwise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Optional<Expense> getExpenseById(Long expenseId) {
        return expenseRepository.findById(expenseId);
    }

    public List<Expense> getExpensesByGroupId(Long groupId) {
        return expenseRepository.findByGroupId(groupId);
    }

    public List<Expense> getExpensesByUserId(Long userId) {
        return userRepository.findById(userId).map(User::getExpenses).orElseThrow();
    }

    public Expense updateExpense(Long expenseId, Expense expenseDetails) {
        Expense expense = expenseRepository.findById(expenseId).orElseThrow();
        expense.setDescription(expenseDetails.getDescription());
        expense.setAmount(expenseDetails.getAmount());
        expense.setPaidBy(expenseDetails.getPaidBy());
        expense.setGroup(expenseDetails.getGroup());
        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long expenseId) {
        expenseRepository.deleteById(expenseId);
    }
}
