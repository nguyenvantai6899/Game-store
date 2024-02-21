package com.gamesstorebe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private Double price;
    private String releaseDate;
    private String status;

    private int downloads;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductImage> productImages = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Categories categories;

    @ManyToMany
    @JoinTable(
            name = "products_featured",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "featured_id")
    )
    @JsonManagedReference
    private List<Features> productsFeatures;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "developer_id")
    private Developers developer;


    @OneToOne(mappedBy = "product")
    @JsonBackReference
    private ProductDiscount productDiscount;

}
