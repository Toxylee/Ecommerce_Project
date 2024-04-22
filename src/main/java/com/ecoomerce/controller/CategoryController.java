package com.ecoomerce.controller;

import com.ecoomerce.dto.CategoryRequest;
import com.ecoomerce.dto.CategoryResponse;
import com.ecoomerce.model.Category;
import com.ecoomerce.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CategoryController {
private final CategoryService categoryService;
    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@RequestBody CategoryRequest request, @RequestParam("merchantCode")String merchantCode){
        return categoryService.addCategory(request,merchantCode);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCategory(@RequestBody CategoryRequest request, @RequestParam("categoryCode")String categoryCode){
        return categoryService.updateCategory(request,categoryCode);
    }

    @PostMapping("/add-product")
    public ResponseEntity<String> addProduct(@RequestParam("productCodes")List<String> productCodes, @RequestParam("categoryCode")String categoryCode){
        return categoryService.addProduct(productCodes,categoryCode);
    }

    @GetMapping("/all")
    public List<Category> allCategory(@RequestParam("merchantCode")String merchantCode){
        return categoryService.listCategory(merchantCode);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCategory(@RequestParam("categoryCode")String categoryCodee){
        return categoryService.deleteCategory(categoryCodee);
    }

    @PutMapping("/config")
    public ResponseEntity<String> updateConfig(@RequestParam("configCode")String configCode,@RequestParam("font")String font,@RequestParam("color")String color,@RequestParam("logoUrl")String logoUrl){
        return categoryService.updateConfiguration(configCode,font,color,logoUrl);
    }

}
