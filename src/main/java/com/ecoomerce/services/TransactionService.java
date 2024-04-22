package com.ecoomerce.services;

import com.ecoomerce.dto.SellRequest;
import com.ecoomerce.model.Merchant;
import com.ecoomerce.model.Product;
import com.ecoomerce.model.Transaction;
import com.ecoomerce.repository.MerchantRepository;
import com.ecoomerce.repository.ProductRepository;
import com.ecoomerce.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;
    private final MerchantRepository merchantRepository;
    public ResponseEntity<String> sellProduct(SellRequest request,String merchantCode) {
        Merchant merchant;

        Optional<Merchant> optionalMerchant = merchantRepository.findByMerchantCode(merchantCode);
        if (optionalMerchant.isEmpty()) {
            return ResponseEntity.badRequest().body("Merchant not found");
        }
        merchant = optionalMerchant.get();
        Optional<Product> optionalProduct = productRepository.findByProductNameAndMerchant_MerchantCode(request.getProductName(),merchantCode);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            int availableQuantity = product.getQty();
            if (availableQuantity >= request.getQuantity()) {
                int newQuantity = availableQuantity - request.getQuantity();
                product.setQty(newQuantity);
                productRepository.save(product);
                // Create and save a transaction record
                Transaction transaction = new Transaction();
                transaction.setProductName(request.getProductName());
                transaction.setQuantity(request.getQuantity());
                transaction.setAmount(product.getUnitPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
                transaction.setDateCreated(new Date());
                transaction.setBatchNo(product.getBatchNumber());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = dateFormat.format(new Date());
                transaction.setTransactionDate(formattedDate);
                transaction.setMerchant(merchant);
                transactionRepository.save(transaction);
                product.setDateSold(new Date());
                productRepository.save(product);
                return ResponseEntity.ok("Sold " + request.getQuantity() + " " + request.getProductName() +" "+"at the price of"+" "+transaction.getAmount());
            } else {
                return ResponseEntity.badRequest().body("Not enough quantity available for " + request.getProductName());
            }
        } else {
            return ResponseEntity.badRequest().body("Product not available for this merchant: " + request.getProductName());
        }
    }
    public List<Transaction> getTransactionsByDate(String transDateTime,String merchantCode)  {

        return transactionRepository.findByTransactionDateAndMerchant_MerchantCode(transDateTime,merchantCode);
    }

    public List<Transaction> getTransactionsByItem(String productName, String merchantCode) {
        return transactionRepository.findByProductNameAndMerchant_MerchantCode(productName, merchantCode);
    }

    public List<Transaction> getTransactionsByBatch(String batchNumber, String merchantCode) {
        return transactionRepository.findByBatchNoAndMerchant_MerchantCode(batchNumber, merchantCode);
    }

    public List<Transaction> getAllTransactions(String merchantCode) {
        return transactionRepository.findAllByMerchant_MerchantCode(merchantCode);
    }


}
