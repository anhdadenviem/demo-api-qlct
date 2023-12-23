package com.example.be.model;

import java.time.LocalDate;

public class Expenditure_FromTo {
    private LocalDate startDate;
    private LocalDate endDate;

    public Expenditure_FromTo(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Expenditure_FromTo() {
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
