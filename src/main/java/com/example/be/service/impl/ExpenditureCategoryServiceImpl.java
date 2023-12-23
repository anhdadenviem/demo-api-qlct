package com.example.be.service.impl;

import com.example.be.model.Expenditure;
import com.example.be.model.ExpenditureCategory;
import com.example.be.repository.ExpenditureCategoryRepository;
import com.example.be.service.ExpenditureCategoryService;
import com.example.be.service.ExpenditureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenditureCategoryServiceImpl implements ExpenditureCategoryService {
    @Autowired
    ExpenditureCategoryRepository expenditureCategoryRepository;

    @Override
    public void save(ExpenditureCategory expenditureCategory) {
        expenditureCategoryRepository.save(expenditureCategory);
    }

    @Override
    public Iterable<ExpenditureCategory> findAll() {
        return expenditureCategoryRepository.findAll();
    }

    @Override
    public Optional<ExpenditureCategory> findById(Long id) {
        return expenditureCategoryRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        expenditureCategoryRepository.deleteById(id);
    }
}
