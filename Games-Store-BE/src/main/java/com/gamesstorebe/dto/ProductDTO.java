package com.gamesstorebe.dto;

import com.gamesstorebe.entity.Categories;
import com.gamesstorebe.entity.Developers;
import com.gamesstorebe.entity.Features;
import lombok.Data;

@Data
public class ProductDTO {
    private int id;
    private String name;
    private String description;
    private String image;
    private String video;
    private Double price;
    private String releaseDate  ;
    private int stock;
    private Categories category;
    private Features feature;
    private Developers developer;
}
