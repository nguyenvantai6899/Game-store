package com.gamesstorebe.service.impl;

import com.gamesstorebe.entity.ProductImage;
import com.gamesstorebe.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageService {
    @Autowired
    private ProductImageRepository productImageRepository;

    public ProductImage getProductImageById (int id){
        return productImageRepository.findById(id).get();
    }

    public List<ProductImage> getProductImagesByProductId (int productId){
        return productImageRepository.getProductImageByProduct_Id(productId);
    }
}
