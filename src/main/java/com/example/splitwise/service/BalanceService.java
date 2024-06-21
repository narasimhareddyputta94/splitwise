package com.example.splitwise.service;

import com.example.splitwise.Repositories.BalanceRepository;
import com.example.splitwise.Repositories.ExpenseRepository;
import com.example.splitwise.Repositories.UserRepository;
import com.example.splitwise.model.Balance;
import com.example.splitwise.model.Expense;
import com.example.splitwise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Balance> getAllBalances() {
        return balanceRepository.findAll();
    }

    public Optional<Balance> getBalanceById(Long balanceId) {
        return balanceRepository.findById(balanceId);
    }

    public List<Balance> getBalancesByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return balanceRepository.findByUser(user);
    }

    public Balance createBalance(Balance balance) {
        return balanceRepository.save(balance);
    }

    public Balance updateBalance(Long balanceId, Balance balanceDetails) {
        Balance balance = balanceRepository.findById(balanceId).orElseThrow();
        balance.setAmount(balanceDetails.getAmount());
        return balanceRepository.save(balance);
    }

    public void deleteBalance(Long balanceId) {
        balanceRepository.deleteById(balanceId);
    }

    public List<Settlement> settleBalances(Long groupId) {
        List<Expense> expenses = expenseRepository.findByGroupId(groupId);
        Map<User, Double> balances = new HashMap<>();

        for (Expense expense : expenses) {
            User payer = expense.getPaidBy();
            double amount = expense.getAmount();
            int totalUsers = expense.getGroup().getMembers().size();
            double shareAmount = amount / totalUsers;

            balances.put(payer, balances.getOrDefault(payer, 0.0) + amount);

            for (User member : expense.getGroup().getMembers()) {
                if (!member.equals(payer)) {
                    balances.put(member, balances.getOrDefault(member, 0.0) - shareAmount);
                }
            }
        }

        PriorityQueue<UserBalance> debtors = new PriorityQueue<>(Comparator.comparingDouble(ub -> ub.amount));
        PriorityQueue<UserBalance> creditors = new PriorityQueue<>((ub1, ub2) -> Double.compare(ub2.amount, ub1.amount));

        for (Map.Entry<User, Double> entry : balances.entrySet()) {
            User user = entry.getKey();
            double amount = entry.getValue();
            if (amount < 0) {
                debtors.offer(new UserBalance(user, -amount));
            } else if (amount > 0) {
                creditors.offer(new UserBalance(user, amount));
            }
        }

        List<Settlement> settlements = new ArrayList<>();
        while (!debtors.isEmpty() && !creditors.isEmpty()) {
            UserBalance debtor = debtors.poll();
            UserBalance creditor = creditors.poll();
            double settledAmount = Math.min(debtor.amount, creditor.amount);
            settlements.add(new Settlement(debtor.user, creditor.user, settledAmount));

            if (debtor.amount > settledAmount) {
                debtors.offer(new UserBalance(debtor.user, debtor.amount - settledAmount));
            }

            if (creditor.amount > settledAmount) {
                creditors.offer(new UserBalance(creditor.user, creditor.amount - settledAmount));
            }
        }

        return settlements;
    }

    private static class UserBalance {
        User user;
        double amount;

        UserBalance(User user, double amount) {
            this.user = user;
            this.amount = amount;
        }
    }

    public static class Settlement {
        private User debtor;
        private User creditor;
        private double amount;

        public Settlement(User debtor, User creditor, double amount) {
            this.debtor = debtor;
            this.creditor = creditor;
            this.amount = amount;
        }

    }
}
