package com.gamesstorebe.repository;

import com.gamesstorebe.dto.ProductDTO;
import com.gamesstorebe.entity.Features;
import com.gamesstorebe.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository(value = "productRepository")
public interface ProductRepository extends JpaRepository<Product, Integer> {

     Optional<Product> deleteProductById(Integer id);
     @Query(value = "select p from Product p join p.productsFeatures f where f.id = :id")
     List<Product> findAllByFeatures(@Param("id") int id);


     @Query(value = "select p, d from Product p " +
             "left join p.productDiscount pd " +
             "left join pd.discount d " +
             "where p.id = :id")
     Object findProductsWithDiscounts(@Param("id") int id);
}
