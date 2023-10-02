package com.vasudev.blog.controllers;

import com.vasudev.blog.payloads.ApiResponse;
import com.vasudev.blog.payloads.CategoryDto;
import com.vasudev.blog.services.CategoryService;
import org.hibernate.secure.spi.IntegrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
    {
       CategoryDto categoryDto1=this.categoryService.createCategory(categoryDto);
       return new ResponseEntity<CategoryDto>(categoryDto1, HttpStatus.CREATED );

    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId)
    {
        CategoryDto categoryDto1 = this.categoryService.updateCategory(categoryDto,categoryId);
        return new  ResponseEntity<CategoryDto>(categoryDto1,HttpStatus.OK);
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId)
    {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted",true),HttpStatus.OK);
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId)
    {
        CategoryDto categoryDto=this.categoryService.getCategory(categoryId);
        return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory()
    {
        List<CategoryDto> categoryDtoList=this.categoryService.getAllCategory();
        return new ResponseEntity<List<CategoryDto>>(categoryDtoList,HttpStatus.OK);
    }




}
