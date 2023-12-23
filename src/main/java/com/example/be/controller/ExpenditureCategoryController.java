package com.example.be.controller;

import com.example.be.model.Expenditure;
import com.example.be.model.ExpenditureCategory;
import com.example.be.repository.ExpenditureCategoryRepository;
import com.example.be.service.ExpenditureCategoryService;
import com.example.be.service.ExpenditureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/expenditure-categories")
public class ExpenditureCategoryController {
    @Autowired
    ExpenditureCategoryService expenditureCategoryService;
    @GetMapping
    public ResponseEntity getAll(){
        return new ResponseEntity<>(expenditureCategoryService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        Optional<ExpenditureCategory> expenditureCategoryOptional = expenditureCategoryService.findById(id);
        return expenditureCategoryOptional.map(expenditureCategory -> new ResponseEntity<>(expenditureCategory, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    public ResponseEntity add(@RequestBody ExpenditureCategory expenditureCategory){
        expenditureCategoryService.save(expenditureCategory);
        return new ResponseEntity<>("Đã thêm thể loại giao dịch thành công",HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id ,@RequestBody ExpenditureCategory expenditureCategory){
        expenditureCategory.setId(id);
        expenditureCategoryService.save(expenditureCategory);
        return new ResponseEntity("Đã sửa thông tin thể loại giao dịch", HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        expenditureCategoryService.remove(id);
        return new ResponseEntity<>("Đã xóa thể loại giao dịch",HttpStatus.OK);
    }
}
