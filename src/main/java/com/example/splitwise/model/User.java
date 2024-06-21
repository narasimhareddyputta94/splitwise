package com.example.splitwise.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String email;
    private String password;

    @ManyToMany(mappedBy = "users")
    private List<Group> groups;

    @OneToMany(mappedBy = "paidBy")
    private List<Expense> expenses;

    @OneToMany(mappedBy = "user")
    private List<Balance> balances;
}
