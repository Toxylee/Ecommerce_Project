package com.ecoomerce.repository;

import com.ecoomerce.model.Category;
import com.ecoomerce.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryName(String name);
    Optional<Category> findByCategoryCode(String code);


    @Query("SELECT c FROM Category c WHERE c.merchant.merchantCode = :merchantCode")
    List<Category> findAllByMerchantCode(@Param("merchantCode") String merchantCode);

}