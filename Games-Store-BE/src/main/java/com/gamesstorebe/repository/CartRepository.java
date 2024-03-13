package com.gamesstorebe.repository;

import com.gamesstorebe.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query(value = "select c from Cart c left join c.user u left join c.products where u.email = :email")
    Cart findCartByUser(@Param("email") String email);
    @Query(value = "select count(c) from Cart c left join c.user u left join c.products where u.email = :email")
    int getQuantity(@Param("email") String email);
}
