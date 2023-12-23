package com.example.be.repository;

import com.example.be.model.ExpenditureCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenditureCategoryRepository extends JpaRepository<ExpenditureCategory,Long> {
}
