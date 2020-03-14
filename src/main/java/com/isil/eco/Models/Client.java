package com.isil.eco.Models;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
@Table(name = "Client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Client_ID")
    private long id;
    @Column(name = "First_Name")
    @NotEmpty(message = "*Please provide your last name")
    @Length(min = 5, message = "*Your First Name must have at least 5 characters")
    private String lname;
    @Column(name = "Last_Name")
    @NotEmpty(message = "*Please provide your last name")
    @Length(min = 5, message = "*Your Last Name must have at least 5 characters")
    private String fname;
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;
    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;
    private String tel;
    private String Role;
    @ManyToMany
    @JoinTable(name="Orders",
            joinColumns=
            @JoinColumn(name="Client_ID"),
            inverseJoinColumns=
            @JoinColumn(name="Product_ID"))
    private Collection<Product> products;



    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public Client() { }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
