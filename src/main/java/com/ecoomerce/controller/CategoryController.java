package com.ecoomerce.controller;

import com.ecoomerce.dto.CategoryRequest;
import com.ecoomerce.dto.CategoryResponse;
import com.ecoomerce.model.Category;
import com.ecoomerce.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CategoryController {
private final CategoryService categoryService;
    @PostMapping("/add")
    public CategoryResponse addCategory(@RequestBody CategoryRequest request){
        return categoryService.addCategory(request);
    }

    @PutMapping("/update")
    public CategoryResponse updateCategory(@RequestBody CategoryRequest request,@RequestParam("id")Long id){
        return categoryService.updateCategory(request,id);
    }

    @GetMapping("/all")
    public List<Category> allCategory(){
        return categoryService.listCategory();
    }

    @DeleteMapping("/delete")
    public String deleteCategory(@RequestParam("categoryCode")String categoryCodee){
        return categoryService.deleteCategory(categoryCodee);
    }

}
