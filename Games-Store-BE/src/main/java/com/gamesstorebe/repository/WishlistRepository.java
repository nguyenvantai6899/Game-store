package com.gamesstorebe.repository;

import com.gamesstorebe.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    List<Wishlist> getWishlistByUserEmail(String user_email);
    Wishlist getWishlistByUserEmailAndProductId(String user_email, int product_id);
}
