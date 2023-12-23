package com.example.be.service;

import com.example.be.model.Budget;
import com.example.be.service.IService;

import java.time.LocalDate;
import java.util.List;

public interface BudgetService extends IService<Budget> {
    boolean checkExist(Long idCategory, Long idUser);
    List<Budget> findAllFromTo(LocalDate startDate, LocalDate endDate);
}
