package com.ecoomerce.controller;

import com.ecoomerce.dto.ProductRequest;
import com.ecoomerce.dto.ProductResponse;
import com.ecoomerce.dto.SellRequest;
import com.ecoomerce.model.Product;
import com.ecoomerce.model.Transaction;
import com.ecoomerce.services.ProductService;
import com.ecoomerce.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TransactionController {
private final TransactionService transactionService;
    @PostMapping("/sell")
    public ResponseEntity<String> sellProduct(@RequestBody SellRequest request, @RequestParam("merchantCode")String merchantCode){
        return transactionService.sellProduct(request,merchantCode);
    }
    @GetMapping("/byDate")
    public List<Transaction> getTransactionsByDate(@RequestParam("transDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS") String transDateTime, @RequestParam("merchantCode")String merchantCode) throws ParseException {
        return transactionService.getTransactionsByDate(transDateTime,merchantCode);
    }
    @GetMapping("/byBatch")
    public List<Transaction> getByBatch(@RequestParam("byBatch") String byBatch,@RequestParam("merchantCode")String merchantCode) {
        return transactionService.getTransactionsByBatch(byBatch,merchantCode);
    }

    @GetMapping("/byItem")
    public List<Transaction> getByItem(@RequestParam("byItem") String byItem,@RequestParam("merchantCode")String merchantCode) {
        return transactionService.getTransactionsByItem(byItem, merchantCode);
    }

    @GetMapping("/all")
    public List<Transaction> getAllTransactions(@RequestParam("merchantCode") String merchantCode) {
        return transactionService.getAllTransactions(merchantCode);
    }
}
