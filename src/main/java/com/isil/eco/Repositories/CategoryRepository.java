package com.isil.eco.Repositories;

import com.isil.eco.Models.Category;
import com.isil.eco.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
