package com.example.be.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Expenditure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    User user;
    @ManyToOne
    ExpenditureCategory expenditureCategory;
    @ManyToOne
    Wallet wallet;
    private long amountSpent;
    private String description;
    private LocalDate localDate;

    public Expenditure() {
    }

    public Expenditure(Long id, User user, ExpenditureCategory expenditureCategory, Wallet wallet, long amountSpent, String description, LocalDate localDate) {
        this.id = id;
        this.user = user;
        this.expenditureCategory = expenditureCategory;
        this.wallet = wallet;
        this.amountSpent = amountSpent;
        this.description = description;
        this.localDate = localDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public long getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(long amountSpent) {
        this.amountSpent = amountSpent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
