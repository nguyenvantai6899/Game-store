package com.gamesstorebe.dto;

import com.gamesstorebe.entity.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private int id;
    private String name;
    private String description;
    private ProductImage productImages;
    private Double price;
    private String releaseDate  ;
    private int downloads;
    private Categories categories;
    private List<Features> productsFeatures;
    private Developers developer;
    private Discount discount;
}
