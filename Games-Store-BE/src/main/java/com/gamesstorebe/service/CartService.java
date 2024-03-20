package com.gamesstorebe.service;

import com.gamesstorebe.dto.CartDTO;
import com.gamesstorebe.entity.Cart;
public interface CartService {
    CartDTO getCartByUserEmail(String userEmail);
    Cart addProductIntoCart( int idProduct, String email);
    Cart removeProductFromCart(int idProduct,  String email);

    Cart removeALlProductFromCart(String email);
    void addCartByUser(String email);
    CartDTO getCartById(int id);

    int getQuantityProductFromCart(String userEmail);
}
