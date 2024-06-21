package com.example.splitwise.service;

import com.example.splitwise.Repositories.ExpenseRepository;
import com.example.splitwise.Repositories.GroupRepository;
import com.example.splitwise.Repositories.UserRepository;
import com.example.splitwise.model.Expense;
import com.example.splitwise.model.Group;
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

    @Autowired
    private GroupRepository groupRepository;

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
        Optional<Group> groupOpt = groupRepository.findById(groupId);
        if (groupOpt.isPresent()) {
            Group group = groupOpt.get();
            return group.getExpenses();
        }
        return null;
    }

    public List<Expense> getExpensesByUserId(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return user.getExpenses();
        }
        return null;
    }

    public Expense updateExpense(Long expenseId, Expense expenseDetails) {
        Optional<Expense> expenseOpt = expenseRepository.findById(expenseId);
        if (expenseOpt.isPresent()) {
            Expense expense = expenseOpt.get();
            expense.setDescription(expenseDetails.getDescription());
            expense.setAmount(expenseDetails.getAmount());
            expense.setPaidBy(expenseDetails.getPaidBy());
            expense.setGroup(expenseDetails.getGroup());
            return expenseRepository.save(expense);
        }
        return null;
    }

    public void deleteExpense(Long expenseId) {
        expenseRepository.deleteById(expenseId);
    }
}
