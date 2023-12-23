package com.example.be.controller;

import com.example.be.model.Budget;
import com.example.be.model.Expenditure;
import com.example.be.model.Expenditure_FromTo;
import com.example.be.service.BudgetService;
import com.example.be.service.ExpenditureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/budgets")
public class BudgetController {
    @Autowired
    BudgetService budgetService;
    ExpenditureService expenditureService;
    @GetMapping
    public ResponseEntity getAll(){
        return new ResponseEntity<>(budgetService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        Optional<Budget> budgetOptional = budgetService.findById(id);
        return budgetOptional.map(budget -> new ResponseEntity<>(budget, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    public ResponseEntity add(@RequestBody Budget budget){
        boolean checkExist = budgetService.checkExist(budget.getExpenditureCategory().getId(),budget.getUser().getId());
        if(checkExist){
            return new ResponseEntity<>("Ví đã có ngân sách",HttpStatus.BAD_REQUEST);
        }
        budgetService.save(budget);
        return new ResponseEntity<>("Đã thêm ngân sách thành công",HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id ,@RequestBody Budget budget){
        budget.setId(id);
        budgetService.save(budget);
        return new ResponseEntity("Đã sửa thông tin ngân sách", HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        budgetService.remove(id);
        return new ResponseEntity<>("Đã xóa ngân sách",HttpStatus.OK);
    }
    @PostMapping("/dateBetween/{idUser}")
    public ResponseEntity getByDateBetween(@PathVariable Long idUser,@RequestBody Expenditure_FromTo expenditureFromTo){
        List<Budget> budgets = budgetService.findAllFromTo(expenditureFromTo.getStartDate(),expenditureFromTo.getEndDate());
        for(int i=0;i<budgets.size();i++){
            if(!(budgets.get(i).getUser().getId() == idUser)){
                budgets.remove(i);
            }
        }
        return new ResponseEntity<>(budgets,HttpStatus.OK);
    }
}
