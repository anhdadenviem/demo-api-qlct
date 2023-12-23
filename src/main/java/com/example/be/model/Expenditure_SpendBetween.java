package com.example.be.model;

import java.time.LocalDate;

public class Expenditure_SpendBetween {
    private  Long startSpend;
    private  Long endSpend;

    public Expenditure_SpendBetween() {
    }

    public Expenditure_SpendBetween(Long startSpend, Long endSpend) {
        this.startSpend = startSpend;
        this.endSpend = endSpend;
    }

    public Long getStartSpend() {
        return startSpend;
    }

    public void setStartSpend(Long startSpend) {
        this.startSpend = startSpend;
    }

    public Long getEndSpend() {
        return endSpend;
    }

    public void setEndSpend(Long endSpend) {
        this.endSpend = endSpend;
    }
}
