package com.example.splitwise.service;

import com.example.splitwise.Repositories.BalanceRepository;
import com.example.splitwise.Repositories.UserRepository;
import com.example.splitwise.model.Balance;
import com.example.splitwise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private UserRepository userRepository;

    public Balance createBalance(Balance balance) {
        return balanceRepository.save(balance);
    }

    public List<Balance> getAllBalances() {
        return balanceRepository.findAll();
    }

    public Optional<Balance> getBalanceById(Long balanceId) {
        return balanceRepository.findById(balanceId);
    }

    public List<Balance> getBalancesByUserId(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return balanceRepository.findByUser(user);
        }
        return null;
    }

    public Balance updateBalance(Long balanceId, Balance balanceDetails) {
        Optional<Balance> balanceOpt = balanceRepository.findById(balanceId);
        if (balanceOpt.isPresent()) {
            Balance balance = balanceOpt.get();
            balance.setAmount(balanceDetails.getAmount());
            balance.setUser(balanceDetails.getUser());
            return balanceRepository.save(balance);
        }
        return null;
    }

    public void deleteBalance(Long balanceId) {
        balanceRepository.deleteById(balanceId);
    }
}
