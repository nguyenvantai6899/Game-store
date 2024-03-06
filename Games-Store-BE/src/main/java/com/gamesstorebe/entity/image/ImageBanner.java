package com.gamesstorebe.entity.image;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gamesstorebe.entity.ProductImage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "image_banner")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageBanner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Transient
    private String path;

    @ManyToOne
    @JsonBackReference
    private ProductImage productImages;
    public String getPath() {
        return "http://localhost:8888/api/v1/file/image/banner/" + name;
    }
}
