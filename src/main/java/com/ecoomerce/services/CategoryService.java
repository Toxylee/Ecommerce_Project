package com.ecoomerce.services;

import com.ecoomerce.dto.CategoryRequest;
import com.ecoomerce.model.Category;
import com.ecoomerce.model.Configuration;
import com.ecoomerce.model.Merchant;
import com.ecoomerce.model.Product;
import com.ecoomerce.repository.CategoryRepository;
import com.ecoomerce.repository.ConfigurationRepository;
import com.ecoomerce.repository.MerchantRepository;
import com.ecoomerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
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
    private final MerchantRepository merchantRepository;
    private final ConfigurationRepository configurationRepository;


    private String generateCode() {
        Random random = new Random();
        int code = random.nextInt(9000) + (1000);
        return "CAT-".concat(String.valueOf(code));
    }

    public ResponseEntity<String>  addCategory(CategoryRequest request, String merchantCode) {
        Merchant merchant;

        Optional<Merchant> optionalMerchant = merchantRepository.findByMerchantCode(merchantCode);
        if (optionalMerchant.isEmpty()) {
            return ResponseEntity.badRequest().body("Merchant not found");
        }
        merchant = optionalMerchant.get();
        Optional<Category> optionalCategory = categoryRepository.findByCategoryName(request.getName());
        if (optionalCategory.isPresent()) {
            return ResponseEntity.badRequest().body("Category name already exist");
        } else {
            Category category = new Category();
            category.setCategoryName(request.getName());
            category.setCategoryCode(generateCode());
            category.setImageUrl(request.getImageUrl());
            category.setDescription(request.getDescription());
            category.setMerchant(merchant);
            category.setDateCreated(new Date());
            categoryRepository.save(category);
            return ResponseEntity.ok().body("Category created successfully");
        }

    }

    public ResponseEntity<String> updateCategory(CategoryRequest request,String categoryCode) {
        Optional<Category> optionalCategory = categoryRepository.findByCategoryCode(categoryCode);
        if (optionalCategory.isEmpty()) {
          return ResponseEntity.badRequest().body("Category not found");
        } else {
            Category category = optionalCategory.get();
            category.setCategoryName(request.getName());
            category.setImageUrl(request.getImageUrl());
            category.setDescription(request.getDescription());
            category.setLastModified(new Date());
            categoryRepository.save(category);
            return ResponseEntity.ok().body("Category updated successfully");
        }
    }

    public List<Category> listCategory(String merchantCode) {
        return categoryRepository.findAllByMerchantCode(merchantCode);
    }

    public ResponseEntity<String> deleteCategory(String categoryCode) {
        Optional<Category> optionalCategory = categoryRepository.findByCategoryCode(categoryCode);
        if (optionalCategory.isEmpty()) {
            return ResponseEntity.badRequest().body("Category not found");
        } else {
            Category category = optionalCategory.get();
            categoryRepository.delete(category);
           return ResponseEntity.ok().body("Category deleted successfully");
        }
    }

    public ResponseEntity<String> addProduct(List<String> productCodes, String categoryCode) {
        Optional<Category> optionalCategory = categoryRepository.findByCategoryCode(categoryCode);
        if (optionalCategory.isEmpty()) {
            return ResponseEntity.badRequest().body("Category not found");
        }
        Category category = optionalCategory.get();
        List<Product> productsToAdd = new ArrayList<>();
        for (String productCode : productCodes) {
            Optional<Product> productOptional = productRepository.findByProductCode(productCode);
            if (productOptional.isEmpty()) {
                return ResponseEntity.badRequest().body("Product not found");
            }
            Product product = productOptional.get();
            product.setCategory(category);
            productsToAdd.add(product);
        }
        List<Product> savedProducts = productRepository.saveAll(productsToAdd);
        category.addProducts(savedProducts);
        categoryRepository.save(category);

        return ResponseEntity.ok().body("Products added to category");
    }

    public ResponseEntity<String> updateConfiguration(String configCode, String colour,String font, String logo){
        Optional<Configuration> configOptional = configurationRepository.findByConfigurationCode(configCode);
        if(configOptional.isEmpty()){
            return ResponseEntity.badRequest().body("Configurstion not found");
        }else {
            Configuration configuration = configOptional.get();
            configuration.setColour(colour);
            configuration.setLogoUrl(logo);
            configuration.setFonts(font);
            configurationRepository.save(configuration);
            return ResponseEntity.ok().body("Configuration updated successfully");
        }

    }


}
