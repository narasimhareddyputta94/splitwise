package com.example.splitwise.Repositories;


import com.example.splitwise.model.Balance;
import com.example.splitwise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
    List<Balance> findByUser(User user);
}
