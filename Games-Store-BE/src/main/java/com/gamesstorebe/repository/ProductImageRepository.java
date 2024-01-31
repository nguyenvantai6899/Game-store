package com.gamesstorebe.repository;

import com.gamesstorebe.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
    List<ProductImage> getProductImageByProduct_Id(int productId);
}
