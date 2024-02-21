package com.gamesstorebe.repository;

import com.gamesstorebe.entity.ProductDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository(value = "productDiscountRepository")
public interface ProductDiscountRepository extends JpaRepository<ProductDiscount, Integer> {


//    @Query(value = "select pd from ProductDiscount pd join pd.discount d order by d.percentDiscount desc limit 8");
//    List<ProductDiscount> getParoducts();

    @Query(value = "select pd from ProductDiscount pd join pd.discount d order by d.percentDiscount desc limit 8")
    List<ProductDiscount> getProducts();
}
