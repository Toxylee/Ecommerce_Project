package com.ecoomerce.services;

import com.ecoomerce.dto.CategoryRequest;
import com.ecoomerce.dto.CategoryResponse;
import com.ecoomerce.exception.BadRequestException;
import com.ecoomerce.model.Category;
import com.ecoomerce.model.Product;
import com.ecoomerce.repository.CategoryRepository;
import com.ecoomerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    private String generateCode() {
        Random random = new Random();
        int code = random.nextInt(9000) + (1000);
        return "CAT-".concat(String.valueOf(code));
    }

    public CategoryResponse addCategory(CategoryRequest request) {
        Optional<Category> optionalCategory = categoryRepository.findByCategoryName(request.getName());
        if (optionalCategory.isPresent()) {
            throw new BadRequestException("Category name already exist");
        } else {
            Category category = new Category();
            category.setCategoryName(request.getName());
            category.setCategoryCode(generateCode());
            category.setImageUrl(request.getImageUrl());
            category.setDescription(request.getDescription());
            category.setDateCreated(new Date());
            categoryRepository.save(category);
            return mapper.map(category, CategoryResponse.class);
        }

    }

    public CategoryResponse updateCategory(CategoryRequest request,String categoryCode) {
        Optional<Category> optionalCategory = categoryRepository.findByCategoryCode(categoryCode);
        if (optionalCategory.isEmpty()) {
            throw new BadRequestException("Category not found");
        } else {
            Category category = optionalCategory.get();
            category.setCategoryName(request.getName());
            category.setImageUrl(request.getImageUrl());
            category.setDescription(request.getDescription());
            category.setLastModified(new Date());
            categoryRepository.save(category);
            return mapper.map(category, CategoryResponse.class);
        }
    }

    public List<Category> listCategory() {
        return categoryRepository.findAll();
    }

    public String deleteCategory(String categoryCode) {
        Optional<Category> optionalCategory = categoryRepository.findByCategoryCode(categoryCode);
        if (optionalCategory.isEmpty()) {
            throw new BadRequestException("Category not found");
        } else {
            Category category = optionalCategory.get();
            categoryRepository.delete(category);
           return "Catgeory deleted successfully";
        }
    }

    public String addProduct(String productCode, String categoryCode) {
        Optional<Product> productOptional = productRepository.findByProductCode(productCode);

        if (productOptional.isEmpty()) {
          throw new BadRequestException("Product not found");
        }

        Optional<Category> optionalCategory = categoryRepository.findByCategoryCode(categoryCode);

        if (optionalCategory.isEmpty()) {
            throw new BadRequestException("Category not found");
        }
        Category category = optionalCategory.get();
        Product product = productOptional.get();
        product.setCategory(category);
        Product productCategory = productRepository.save(product);
        category.addProduct(productCategory);
        categoryRepository.save(category);
        return "Product added to category";
    }


}
