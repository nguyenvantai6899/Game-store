package com.gamesstorebe.service.impl;

import com.gamesstorebe.dto.CartDTO;
import com.gamesstorebe.dto.ProductDTO;
import com.gamesstorebe.entity.Cart;
import com.gamesstorebe.entity.Product;
import com.gamesstorebe.entity.User;
import com.gamesstorebe.repository.CartRepository;
import com.gamesstorebe.repository.ProductRepository;
import com.gamesstorebe.repository.UserRepository;
import com.gamesstorebe.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "cartService")

public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository, ModelMapper mapper) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public CartDTO getCartByUserEmail(String userEmail) {
        Optional<Cart> optionalCart = Optional.ofNullable(cartRepository.findCartByUser(userEmail));
        return optionalCart.map(cart -> mapper.map(cart, CartDTO.class)).orElse(null);
    }

    @Override
    public Cart addProductIntoCart(int idProduct, String email) {
        Cart cart = cartRepository.findCartByUser(email);
        Product product = productRepository.findById(idProduct).get();
        List<Product> products = cart.getProducts();
        boolean productAlreadyInCart = products.stream().anyMatch(p -> p.getId() == idProduct);
        if (!productAlreadyInCart) {
            products.add(product);
            cart.setProducts(products);
            cartRepository.save(cart);
        }
        return cart;
    }

    @Override
    public Cart removeProductFromCart(int idProduct, String email) {
        Cart cart = cartRepository.findCartByUser(email);
        Product product = productRepository.findById(idProduct).get();
        List<Product> products = cart.getProducts();
        boolean productAlreadyInCart = products.stream().anyMatch(p -> p.getId() == idProduct);
        if (productAlreadyInCart) {
            products.remove(product);
            cart.setProducts(products);
            cartRepository.save(cart);
        }
        return cart;
    }

    @Override
    public void addCartByUser(String email) {
        Cart cart = new Cart();
        User user = userRepository.findByEmail(email).orElseThrow(null);
        cart.setUser(user);
        cartRepository.save(cart);
    }

    @Override
    public Cart getCartById(int id) {
        return cartRepository.findById(id).orElseThrow(null);
    }
}
