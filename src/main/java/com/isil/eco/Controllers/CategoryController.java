package com.isil.eco.Controllers;

import com.isil.eco.Exceptions.ClientValidationException;
import com.isil.eco.Models.Category;
import com.isil.eco.Services.CategoryService;
import com.isil.eco.helpers.ModelValidator;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("category")
@CrossOrigin(origins = {"*"})
public class CategoryController {
    CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    List<Category> all() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/")
    Category newCategory(@RequestBody @Valid Category category, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ClientValidationException(ModelValidator.getErrorsFromBindingResult(bindingResult));
        }
        return categoryService.saveCategory(category);
    }

    @GetMapping("/{id}")
    Category one(@PathVariable Long id) {
        return categoryService.getOneCategory(id);
    }

    @PutMapping("/{id}")
    Category updateCategory(@RequestBody Category category, @PathVariable Long id) {
        return categoryService.updateCategory(category,id);
    }

    @DeleteMapping("/{id}")
    void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

}
