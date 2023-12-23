package com.example.be.repository;

import com.example.be.model.Budget;
import com.example.be.model.Expenditure;
import com.example.be.model.ExpenditureCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget,Long> {
    boolean existsByExpenditureCategoryIdAndUserId(Long idCategory,Long idUser);
    List<Budget> findAllByCreateAtBetween(LocalDate startDate,LocalDate endDate);
}
