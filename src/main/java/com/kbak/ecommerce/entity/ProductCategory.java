package com.kbak.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "product_category")
// @Data -bug, when you are using relation like ont to many or many to one, use @getter and @setter instead
@Getter
@Setter
public class ProductCategory {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category" )
    private Set<Product> products;

}
/*

@OntToMany - one category to many products
 */