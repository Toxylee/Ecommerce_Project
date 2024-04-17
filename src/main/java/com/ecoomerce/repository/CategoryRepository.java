package com.ecoomerce.repository;

import com.ecoomerce.model.Category;
import com.ecoomerce.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryName(String name);
    Optional<Category> findByCategoryCode(String code);

}