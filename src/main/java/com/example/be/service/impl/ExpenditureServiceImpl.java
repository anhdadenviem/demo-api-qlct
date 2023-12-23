package com.example.be.service.impl;

import com.example.be.model.Expenditure;
import com.example.be.repository.ExpenditureRepository;
import com.example.be.service.ExpenditureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenditureServiceImpl implements ExpenditureService {
    @Autowired
    ExpenditureRepository expenditureRepository;
    @Override
    public void save(Expenditure expenditure) {
        expenditureRepository.save(expenditure);
    }

    @Override
    public Iterable<Expenditure> findAll() {
        return expenditureRepository.findAll();
    }

    @Override
    public Optional<Expenditure> findById(Long id) {
        return expenditureRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        expenditureRepository.deleteById(id);
    }

    @Override
    public List<Expenditure> findAllByLocalDateBetween(LocalDate startDate, LocalDate endDate) {
        return expenditureRepository.findAllByLocalDateBetween(startDate,endDate);
    }

    @Override
    public List<Expenditure> findAllBySpentBetween(Long startSpend, Long endSpend) {
        return expenditureRepository.findAllByAmountSpentBetween(startSpend,endSpend);
    }

    @Override
    public List<Expenditure> findAllByWalletId(Long id) {
        return expenditureRepository.findAllByWalletId(id);
    }

    @Override
    public List<Expenditure> findAllByLocalDate(LocalDate localDate) {
        return expenditureRepository.findAllByLocalDate(localDate);
    }

    @Override
    public List<Expenditure> findAllByExpenditureCategoryId(Long id) {
        return expenditureRepository.findAllByExpenditureCategoryId(id);
    }


}
