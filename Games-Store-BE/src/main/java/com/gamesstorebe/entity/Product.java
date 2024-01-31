package com.gamesstorebe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.*;

import java.util.HashSet;
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
    private int stock;
    private boolean status;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductImage> productImages = new HashSet<>();

    @ManyToOne
    @JsonBackReference
    private Categories categories;

    @ManyToMany
    @JoinTable(
            name = "products_featured",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "featured_id")
    )
    private Set<Features> productsFeatures;

    @ManyToOne
    @JoinColumn(name = "developer_id")
    private Developers developer;


}
