package com.example.be.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    User user;
    @ManyToOne
    private ExpenditureCategory expenditureCategory;
    private long maxBudget;
    private LocalDate createAt;

    public Budget() {
    }

    public Budget(Long id, User user, ExpenditureCategory expenditureCategory, long maxBudget, LocalDate createAt) {
        this.id = id;
        this.user = user;
        this.expenditureCategory = expenditureCategory;
        this.maxBudget = maxBudget;
        this.createAt = createAt;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ExpenditureCategory getExpenditureCategory() {
        return expenditureCategory;
    }

    public void setExpenditureCategory(ExpenditureCategory expenditureCategory) {
        this.expenditureCategory = expenditureCategory;
    }

    public long getMaxBudget() {
        return maxBudget;
    }

    public void setMaxBudget(long maxBudget) {
        this.maxBudget = maxBudget;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
