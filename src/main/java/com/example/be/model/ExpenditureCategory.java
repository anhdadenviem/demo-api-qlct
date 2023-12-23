package com.example.be.model;

import javax.persistence.*;

@Entity
public class ExpenditureCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    User user;
    private String name;

    public ExpenditureCategory() {
    }

    public ExpenditureCategory(User user, Long id, String name) {
        this.user = user;
        this.id = id;
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
