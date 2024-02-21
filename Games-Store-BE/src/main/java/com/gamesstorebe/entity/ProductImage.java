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
//    private String name;
//    @Transient
//    private String iconPath;
//    private String bannerPath;
//    private String avatarPath;
    @ManyToOne
    @JoinColumn(name = "banner_id")
    private ImageBanner imageBanners;

    @ManyToOne
    @JoinColumn(name = "avatar_id")
    private ImageAvatar imageAvatars;

    @ManyToOne
    @JoinColumn(name = "icon_id")
    private ImageIcon imageIcons;

    @ManyToOne
    @JsonBackReference
    private Product product;

//    public String getPath() {
//        return "http://localhost:8888/api/v1/file/image/" + name;
//    }
//
//    public String getIconPath() {
//        return "http://localhost:8888/api/v1/file/image/icon/" + name;
//    }
//    public String getBannerPath() {
//        return "http://localhost:8888/api/v1/file/image/banner/" + name;
//    }
//    public String getAvatarPath() {
//        return "http://localhost:8888/api/v1/file/image/avatar/" + name;
//    }
}
