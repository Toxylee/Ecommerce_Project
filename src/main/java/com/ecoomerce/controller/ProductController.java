package com.ecoomerce.controller;

import com.ecoomerce.dto.CategoryRequest;
import com.ecoomerce.dto.CategoryResponse;
import com.ecoomerce.dto.ProductRequest;
import com.ecoomerce.dto.ProductResponse;
import com.ecoomerce.model.Category;
import com.ecoomerce.model.Product;
import com.ecoomerce.services.CategoryService;
import com.ecoomerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {
private final ProductService productService;
    @PostMapping("/add")
    public ProductResponse addProduct(@RequestBody ProductRequest request, @RequestParam("merchantCode")String merchantCode){
        return productService.addProduct(request,merchantCode);
    }

    @PutMapping("/update")
    public ProductResponse updateProduct(@RequestBody ProductRequest request,@RequestParam("merchantCode")String merchantCode){
        return productService.updateProduct(request,merchantCode);
    }

    @GetMapping("/all")
    public List<Product> allProduct(){
        return productService.productList();
    }


}
