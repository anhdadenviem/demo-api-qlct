package com.example.be.service.impl;

import com.example.be.model.Budget;
import com.example.be.repository.BudgetRepository;
import com.example.be.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetServiceImpl implements BudgetService {
    @Autowired
    BudgetRepository budgetRepository;
    @Override
    public void save(Budget budget) {
        budgetRepository.save(budget);
    }

    @Override
    public Iterable<Budget> findAll() {
        return budgetRepository.findAll();
    }

    @Override
    public Optional<Budget> findById(Long id) {
        return budgetRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        budgetRepository.deleteById(id);
    }


    @Override
    public boolean checkExist(Long idCategory, Long idUser) {
        return budgetRepository.existsByExpenditureCategoryIdAndUserId(idCategory,idUser);
    }

    @Override
    public List<Budget> findAllFromTo(LocalDate startDate, LocalDate endDate) {
        return budgetRepository.findAllByCreateAtBetween(startDate,endDate);
    }
}
