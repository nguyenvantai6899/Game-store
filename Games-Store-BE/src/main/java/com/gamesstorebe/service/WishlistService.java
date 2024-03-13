package com.gamesstorebe.service;

import com.gamesstorebe.dto.WishlistDTO;
import com.gamesstorebe.entity.Product;
import com.gamesstorebe.entity.Wishlist;

import java.util.List;

public interface WishlistService {
    List<WishlistDTO> getWishlistByUser(String user);
    List<WishlistDTO> addProductIntoWishList (int productId, String email);
    List<WishlistDTO> removeProductFromWishList (int productId, String email);

    WishlistDTO getWishlistByEmailProductID (int productId, String email);

}
