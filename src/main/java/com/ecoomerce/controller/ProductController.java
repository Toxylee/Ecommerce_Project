package com.ecoomerce.controller;

import com.ecoomerce.dto.*;
import com.ecoomerce.model.Category;
import com.ecoomerce.model.Product;
import com.ecoomerce.services.CategoryService;
import com.ecoomerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.hibernate.event.spi.PreDeleteEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {
private final ProductService productService;
    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody ProductRequest request, @RequestParam("merchantCode")String merchantCode){
        return productService.addProduct(request,merchantCode);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody ProductRequest request,@RequestParam("merchantCode")String merchantCode){
        return productService.updateProduct(request,merchantCode);
    }

    @GetMapping("/all")
    public List<Product> allProduct(@RequestParam("merchantCode")String merchantCode){
        return productService.productList(merchantCode);
    }

    @GetMapping("/by-category")
    public List<Product> getByCategory(@RequestParam("categoryCode") String categoryCode) {
       return productService.getProductsByCategory(categoryCode);
    }
}
