package com.gamesstorebe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Transient
    private String path;

    @ManyToOne
    @JsonBackReference
    private Product product;

    public String getPath() {
        return "http://localhost:8888/api/v1/file/image/" + name;
    }

}
