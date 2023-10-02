package com.vasudev.blog.services.Impl;

import com.vasudev.blog.entities.Category;
import com.vasudev.blog.exceptions.ResourceNotFoundException;
import com.vasudev.blog.payloads.CategoryDto;
import com.vasudev.blog.repo.CategoryRepo;
import com.vasudev.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Service

public class CategoryServiceImpl implements CategoryService {
    @Autowired
   private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
       Category category= this.modelMapper.map(categoryDto, Category.class);
       Category category1= categoryRepo.save(category);
        return this.modelMapper.map(category1, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryID",categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category category1= this.categoryRepo.save(category);

        return this.modelMapper.map(category1,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category ID", categoryId));
        this.categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category ","Category Id",categoryId));

        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categoryList= this.categoryRepo.findAll();
       List<CategoryDto>categoryDtoList= categoryList.stream().map((category )-> this.modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
        return categoryDtoList;
    }
}
