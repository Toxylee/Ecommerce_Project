package com.ecoomerce.services;

import com.ecoomerce.dto.ProductRequest;
import com.ecoomerce.dto.ProductResponse;
import com.ecoomerce.exception.BadRequestException;
import com.ecoomerce.model.Category;
import com.ecoomerce.model.Merchant;
import com.ecoomerce.model.Product;
import com.ecoomerce.repository.CategoryRepository;
import com.ecoomerce.repository.MerchantRepository;
import com.ecoomerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final MerchantRepository merchantRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    private String bathchNumber() {
        Random random = new Random();
        int batchNumber = random.nextInt(9000) + 1000;
        return "B-".concat(String.valueOf(batchNumber));
    }

    private String productCode() {
        Random random = new Random();
        int productCode = random.nextInt(9000) + 1000;
        return "P-".concat(String.valueOf(productCode));
    }

    public ProductResponse addProduct(ProductRequest productRequest, String merchantCode) {
        Merchant merchant;
        Category category;
        Optional<Merchant> optionalMerchant = merchantRepository.findByMerchantCode(merchantCode);
        if (optionalMerchant.isEmpty()) {
            throw new BadRequestException("Merchant not found");
        }
        merchant = optionalMerchant.get();

        Optional<Category> optionalCategory = categoryRepository.findByCategoryCode(productRequest.getCategoryCode());
        if (optionalCategory.isEmpty()) {
            throw new BadRequestException("Category not found");
        }
        category = optionalCategory.get();

        Optional<Product> productOptional = productRepository.findByProductName(productRequest.getProductName());
        if (productOptional.isPresent()) {
            throw new BadRequestException("Product name already exist");
        } else {
            Product product = new Product();
            product.setMerchant(merchant);
            product.setCategory(category);
            product.setProductCode(productCode());
            product.setBatchNumber(bathchNumber());
            product.setProductDescription(productRequest.getProductDescription());
            product.setProductImageUrl(productRequest.getProductImageUrl());
            product.setQty(productRequest.getQty());
            product.setSellingPrice(productRequest.getSellingPrice());
            product.setUnitPrice(product.getSellingPrice().divide(BigDecimal.valueOf(product.getQty()), 2, RoundingMode.HALF_UP));
            product.setCostPrice(productRequest.getCostPrice());
            product.setProductName(productRequest.getProductName());
            product.setBrandName(productRequest.getBrandName());
            product.setDateCreated(new Date());
            productRepository.save(product);
            return modelMapper.map(product, ProductResponse.class);
        }

    }


    public ProductResponse updateProduct(ProductRequest productRequest, String merchantCode) {
        Merchant merchant;
        Category category;
        Optional<Merchant> optionalMerchant = merchantRepository.findByMerchantCode(merchantCode);
        if (optionalMerchant.isEmpty()) {
            throw new BadRequestException("Merchant not found");
        }
        merchant = optionalMerchant.get();

        Optional<Category> optionalCategory = categoryRepository.findByCategoryCode(productRequest.getCategoryCode());
        if (optionalCategory.isEmpty()) {
            throw new BadRequestException("Category not found");
        }
        category = optionalCategory.get();

        Optional<Product> productOptional = productRepository.findByProductName(productRequest.getProductName());
        if (productOptional.isEmpty()) {
            throw new BadRequestException("Product not found");
        } else {
            Product product = productOptional.get();
            product.setMerchant(merchant);
            product.setCategory(category);
            product.setProductCode(productCode());
            product.setBatchNumber(bathchNumber());
            product.setProductDescription(productRequest.getProductDescription());
            product.setProductImageUrl(productRequest.getProductImageUrl());
            product.setQty(productRequest.getQty());
            product.setSellingPrice(productRequest.getSellingPrice());
            product.setUnitPrice(product.getSellingPrice().divide(BigDecimal.valueOf(product.getQty()), 2, RoundingMode.HALF_UP));
            product.setCostPrice(productRequest.getCostPrice());
            product.setProductName(productRequest.getProductName());
            product.setBrandName(productRequest.getBrandName());
            product.setLastModified(new Date());
            productRepository.save(product);
            return modelMapper.map(product, ProductResponse.class);
        }

    }

    public List<Product> productList(){
        return productRepository.findAll();
    }
}
