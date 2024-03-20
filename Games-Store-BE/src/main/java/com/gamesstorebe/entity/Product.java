package com.gamesstorebe.entity;

import com.fasterxml.jackson.annotation.*;
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

    @OneToOne(mappedBy = "product")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ProductImage productImages;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JoinColumn(name = "categories_id")
    private Categories categories;

    @ManyToMany
    @JsonBackReference
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JoinTable(
            name = "products_featured",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "featured_id")
    )
    private List<Features> productsFeatures;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JoinColumn(name = "developer_id")
    private Developers developer;


    @OneToOne(mappedBy = "product")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonBackReference
    private ProductDiscount productDiscount;

    @ManyToMany(mappedBy = "products")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonBackReference
    private List<Cart> carts;

    @ManyToMany(mappedBy = "productCheckout")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonBackReference
    private List<Checkout> checkouts;
}
