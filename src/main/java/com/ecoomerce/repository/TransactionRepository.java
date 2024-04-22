package com.ecoomerce.repository;

import com.ecoomerce.model.Category;
import com.ecoomerce.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByTransactionDateAndMerchant_MerchantCode(String transactionDate,String merchantCode);
List<Transaction> findByProductNameAndMerchant_MerchantCode (String productName, String merchantCode);
List<Transaction> findByBatchNoAndMerchant_MerchantCode (String batchNo, String merchantCode);
List<Transaction> findAllByMerchant_MerchantCode (String merchantCode);

}