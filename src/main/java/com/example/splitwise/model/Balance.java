package com.example.splitwise.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Entity
@Data
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double amount;
}
