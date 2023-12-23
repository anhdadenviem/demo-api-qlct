package com.example.be.repository;

import com.example.be.model.Expenditure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenditureRepository extends JpaRepository<Expenditure,Long> {
    List<Expenditure> findAllByLocalDateBetween(LocalDate startDate, LocalDate endDate);
    List<Expenditure> findAllByAmountSpentBetween(Long startSpend, Long endSpend);
    List<Expenditure> findAllByWalletId(Long id);
    List<Expenditure> findAllByLocalDate(LocalDate localDate);
    List<Expenditure> findAllByExpenditureCategoryId(Long id);
}
