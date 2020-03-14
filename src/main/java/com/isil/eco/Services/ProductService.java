package com.isil.eco.Services;

import com.isil.eco.Exceptions.ModelNotFoundException;
import com.isil.eco.Models.Product;
import com.isil.eco.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getOneProduct(long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Product" ,id));
    }
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }
    public Product updateProduct(Product product,long id){
        return productRepository.findById(id)
                .map(p -> {
                    p.setName(product.getName());
                    p.setDescription(product.getDescription());
                    p.setPrice(product.getPrice());
                    p.setQte(p.getQte());
                    return productRepository.save(product);
                })
                .orElseGet(()->null);
    }
    public void deleteProduct(long id){
       productRepository.deleteById(id);
    }
}
