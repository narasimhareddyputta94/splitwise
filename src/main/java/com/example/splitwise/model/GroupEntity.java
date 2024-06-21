package com.example.splitwise.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "groups")
    private List<User> members;

    // Getters and Setters if not using Lombok



// Getters and Setters if not using Lombok

    public List<User> getMembers() {
        return members;
    }

    public List<User> getUsers() {
        return members;
    }

    public List<Expense> getExpenses() {
        return null;
    }
}
