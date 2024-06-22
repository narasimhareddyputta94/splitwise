package com.example.splitwise.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    private String password;

    @ManyToMany
    @JoinTable(
            name = "group_members",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<GroupEntity> groups;

    @OneToMany(mappedBy = "paidBy")
    private List<Expense> expenses;

    // No more encoding in the setPassword method
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
