package com.example.be.controller;

import com.example.be.model.Expenditure;
import com.example.be.model.Expenditure_FromTo;
import com.example.be.model.Expenditure_SpendBetween;
import com.example.be.model.Wallet;
import com.example.be.repository.ExpenditureRepository;
import com.example.be.service.ExpenditureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/expenditures")
public class ExpenditureController {
    @Autowired
    ExpenditureService expenditureService;
    @GetMapping
    public ResponseEntity getAll(){
        return new ResponseEntity<>(expenditureService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        Optional<Expenditure> expenditureOptional = expenditureService.findById(id);
        return expenditureOptional.map(expenditure -> new ResponseEntity<>(expenditure, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    public ResponseEntity add(@RequestBody Expenditure expenditure){
        expenditureService.save(expenditure);
        return new ResponseEntity<>("Đã thêm giao dịch thành công",HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id ,@RequestBody Expenditure expenditure){
        expenditure.setId(id);
        expenditureService.save(expenditure);
        return new ResponseEntity("Đã sửa thông tin giao dịch", HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        expenditureService.remove(id);
        return new ResponseEntity<>("Đã xóa giao dịch",HttpStatus.OK);
    }
    @PostMapping("/date/{idUser}")
    public ResponseEntity findByDateBetween(@PathVariable Long idUser,@RequestBody Expenditure_FromTo expenditureFromTo){
        List<Expenditure> expenditures = expenditureService.findAllByLocalDateBetween(expenditureFromTo.getStartDate(),expenditureFromTo.getEndDate());
        for(int i=0;i<expenditures.size();i++){
            if(!(expenditures.get(i).getUser().getId() == idUser)){
                expenditures.remove(i);
            }
        }
        return new ResponseEntity<>(expenditures,HttpStatus.OK);
    }
    @PostMapping("/dateAndWallet/{idUser}/{idWallet}")
    public ResponseEntity findByDateBetweenAndWallet(@PathVariable Long idUser,@PathVariable Long idWallet, @RequestBody Expenditure_FromTo expenditureFromTo){
        List<Expenditure> expenditures = expenditureService.findAllByLocalDateBetween(expenditureFromTo.getStartDate(),expenditureFromTo.getEndDate());
        for(int i=0;i<expenditures.size();i++){
            if(!(expenditures.get(i).getUser().getId() == idUser)||!(expenditures.get(i).getWallet().getId() == idWallet)){
                expenditures.remove(i);
            }
        }
        return new ResponseEntity<>(expenditures,HttpStatus.OK);
    }
    @PostMapping("/todayAndWallet/{idUser}/{idWallet}")
    public ResponseEntity findByTodayAndWallet(@PathVariable Long idUser,@PathVariable Long idWallet, @RequestBody LocalDate localDate){
        List<Expenditure> expenditures = expenditureService.findAllByLocalDate(localDate);
        for(int i=0;i<expenditures.size();i++){
            if(!(expenditures.get(i).getUser().getId() == idUser)||!(expenditures.get(i).getWallet().getId() == idWallet)){
                expenditures.remove(i);
            }
        }
        return new ResponseEntity<>(expenditures,HttpStatus.OK);
    }
    @GetMapping("/expenditureCategory/{idCategory}")
    public ResponseEntity findByCategory(@PathVariable Long idCategory){
        List<Expenditure> expenditures = expenditureService.findAllByExpenditureCategoryId(idCategory);
        return new ResponseEntity<>(expenditures,HttpStatus.OK);
    }
    @PostMapping("/byDate")
    public ResponseEntity findByDate(@RequestBody LocalDate localDate){
        List<Expenditure> expenditures = expenditureService.findAllByLocalDate(localDate);
        return new ResponseEntity<>(expenditures,HttpStatus.OK);
    }
    @GetMapping("/wallet/{id}")
    public ResponseEntity findByWalletId(@PathVariable Long id){
        List<Expenditure> expenditures = expenditureService.findAllByWalletId(id);
        return new ResponseEntity<>(expenditures,HttpStatus.OK);
    }
    @PostMapping("/spendBetween")
    public ResponseEntity findBySpendBetween(@RequestBody Expenditure_SpendBetween expenditureSpendBetween){
        List<Expenditure> expenditures = expenditureService.findAllBySpentBetween(expenditureSpendBetween.getStartSpend(),expenditureSpendBetween.getEndSpend());
        return new ResponseEntity<>(expenditures,HttpStatus.OK);
    }
}
