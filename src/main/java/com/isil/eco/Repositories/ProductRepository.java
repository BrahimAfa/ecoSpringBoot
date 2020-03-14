package com.isil.eco.Repositories;

import com.isil.eco.Models.Client;
import com.isil.eco.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
