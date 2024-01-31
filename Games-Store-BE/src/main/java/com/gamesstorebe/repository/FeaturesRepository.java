package com.gamesstorebe.repository;

import com.gamesstorebe.entity.Features;
import com.gamesstorebe.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface FeaturesRepository extends JpaRepository<Features, Integer> {
    @Query("select f from Features f join f.productsFeatures p where p.id = :productId")
    List<Features> findAllByProduct(@Param("productId") int productId);
}
