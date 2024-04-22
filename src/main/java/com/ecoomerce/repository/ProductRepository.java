package com.ecoomerce.repository;

import com.ecoomerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
Optional<Product> findByProductName (String productName);
Optional<Product> findByProductCode (String productCode);
List<Product> findByCategory_CategoryCode(String categoryCode);

    @Query("SELECT p FROM Product p WHERE p.merchant.merchantCode = :merchantCode")
    List<Product> findAllByMerchantCode(@Param("merchantCode") String merchantCode);

    @Query("SELECT p FROM Product p WHERE p.productName= :productName AND p.merchant.merchantCode = :merchantCode")
    Optional<Product> findByProductNameAndMerchant_MerchantCode(@Param("productName") String productName,@Param("merchantCode") String merchantCode);


}