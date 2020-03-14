package com.isil.eco.Services;

import com.isil.eco.Exceptions.ModelNotFoundException;
import com.isil.eco.Models.Category;
import com.isil.eco.Models.Product;
import com.isil.eco.Repositories.CategoryRepository;
import com.isil.eco.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getOneCategory(long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Category" ,id));
    }

    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }
    public Category updateCategory(Category category,long id){
        return categoryRepository.findById(id)
                .map(c -> {
                    c.setName(category.getName());
                    return categoryRepository.save(category);
                })
                .orElseGet(()->null);
    }
    public void deleteCategory(long id){
        categoryRepository.deleteById(id);
    }
}
