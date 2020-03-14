package com.isil.eco.Models;

import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Product_ID")
    private long id;
    @NotEmpty(message = "*Please provide Product Name")
    private String name;
    @Length(min = 5, message = "*Description must have at least 5 characters")
    @NotEmpty(message = "*description must not be empty")
    private String description;
    private double price;
    @Positive(message = "*Qte must be positive")
    private int qte;
    @ManyToOne
    @JoinColumn(name="category_ID")
    private Category category;

    public Product() { }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}
