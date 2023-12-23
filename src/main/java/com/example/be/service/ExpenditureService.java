package com.example.be.service;

import com.example.be.model.Expenditure;
import com.example.be.service.IService;

import java.time.LocalDate;
import java.util.List;

public interface ExpenditureService extends IService<Expenditure> {
    List<Expenditure> findAllByLocalDateBetween(LocalDate startDate, LocalDate endDate);
    List<Expenditure> findAllBySpentBetween(Long startSpend, Long endSpend);
    List<Expenditure> findAllByWalletId(Long id);
    List<Expenditure> findAllByLocalDate(LocalDate localDate);
    List<Expenditure> findAllByExpenditureCategoryId(Long id);


}
