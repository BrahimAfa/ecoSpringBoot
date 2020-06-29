package com.isil.eco.Controllers;

import com.isil.eco.Exceptions.ClientValidationException;
import com.isil.eco.Models.Product;
import com.isil.eco.Services.ProductService;
import com.isil.eco.helpers.ModelValidator;
import com.isil.eco.security.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/")
    List<Product> all() {
        UserDetailsImpl userDetails =
                (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return productService.getAllProducts();
    }

    @PostMapping("/add")
    Product newProduct(@RequestBody @Valid Product product, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ClientValidationException(ModelValidator.getErrorsFromBindingResult(bindingResult));
        }
        return productService.saveProduct(product);
    }

    @GetMapping("/find/{id}")
    Product one(@PathVariable Long id) {
        return productService.getOneProduct(id);
    }

    @PutMapping("/update/{id}")
    Product replaceProduct(@RequestBody Product product, @PathVariable Long id) {
        return productService.updateProduct(product,id);
    }

    @DeleteMapping("/delete/{id}")
    void deleteProduct(@PathVariable Long id) {
           productService.deleteProduct(id);
    }

}
