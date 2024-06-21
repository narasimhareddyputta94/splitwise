package com.example.splitwise.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Entity
@Data
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "paid_by_id")
    private User paidBy;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
}
