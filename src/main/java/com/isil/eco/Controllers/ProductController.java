package com.isil.eco.Controllers;

import com.isil.eco.Exceptions.ClientValidationException;
import com.isil.eco.Models.Product;
import com.isil.eco.Services.ProductService;
import com.isil.eco.helpers.ModelValidator;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("product")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/")
    List<Product> all() {
        return productService.getAllProducts();
    }

    @PostMapping("/")
    Product newProduct(@RequestBody @Valid Product product, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ClientValidationException(ModelValidator.getErrorsFromBindingResult(bindingResult));
        }
        return productService.saveProduct(product);
    }

    @GetMapping("/{id}")
    Product one(@PathVariable Long id) {
        return productService.getOneProduct(id);
    }

    @PutMapping("/{id}")
    Product replaceProduct(@RequestBody Product product, @PathVariable Long id) {
        return productService.updateProduct(product,id);
    }

    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Long id) {
           productService.deleteProduct(id);
    }

}
