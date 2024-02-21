package com.gamesstorebe.service.impl;

import com.gamesstorebe.customHandleError.system.Result;
import com.gamesstorebe.entity.Categories;
import com.gamesstorebe.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    public List<Categories> categories(){
        return categoriesRepository.findAll();
    }

    public Result getCategoriesById(int categoryId){
        try {
            Optional<Categories> categories = categoriesRepository.findById(categoryId);
            if (categories.isPresent()){
                return new Result(true, HttpStatus.OK, "Find category", null);
            }
            else {
                return new Result(false, HttpStatus.NOT_FOUND, "Category not found", null);
            }
        }
        catch (Exception e){
            return new Result(false, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
    }

    public Result getAllCategories(){
        try {
            Optional<List<Categories>> categories = Optional.of(categoriesRepository.findAll());
            return new Result(true, HttpStatus.OK, "Find category", categories);
        }
        catch (Exception e){
            return new Result(false, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
    }

    public Result saveCategory(Categories categories){
        try {
            if (categories.getId() == 0){
                categoriesRepository.save(categories);
                return new Result (true, HttpStatus.OK, "Create category successfully", categories);
            }else{
                Categories categoryPresented = categoriesRepository.findById(categories.getId()).get();
                categoryPresented.setCategoryName(categories.getCategoryName());
                categoriesRepository.save(categoryPresented);
                return new Result (true, HttpStatus.OK, "Edit category successfully", categoryPresented);
            }
        }catch (Exception e){
            return new Result(false, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
    }
}
