package com.gamesstorebe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gamesstorebe.entity.image.ImageAvatar;
import com.gamesstorebe.entity.image.ImageBanner;
import com.gamesstorebe.entity.image.ImageIcon;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

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
    @OneToMany(mappedBy = "productImages")
    private List<ImageBanner> imageBanners;
    @OneToMany(mappedBy = "productImages")
    private List<ImageAvatar> imageAvatars;
    @OneToMany(mappedBy = "productImages")
    private List<ImageIcon> imageIcons;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "product_id")
    private Product product;

}
