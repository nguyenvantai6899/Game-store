package com.gamesstorebe.service.impl;

import com.gamesstorebe.dto.CartDTO;
import com.gamesstorebe.dto.ProductDTO;
import com.gamesstorebe.dto.WishlistDTO;
import com.gamesstorebe.entity.Cart;
import com.gamesstorebe.entity.Product;
import com.gamesstorebe.entity.User;
import com.gamesstorebe.entity.Wishlist;
import com.gamesstorebe.repository.ProductRepository;
import com.gamesstorebe.repository.UserRepository;
import com.gamesstorebe.repository.WishlistRepository;
import com.gamesstorebe.service.WishlistService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "wishListService")
public class WishListServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper ;

    public WishListServiceImpl(WishlistRepository wishlistRepository, ProductRepository productRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.wishlistRepository = wishlistRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<WishlistDTO> getWishlistByUser(String email) {
        return wishlistDTOS(email);
    }


    @Override
    public List<WishlistDTO> addProductIntoWishList(int productId, String email) {
        Wishlist wishlist = new Wishlist();
        Product product = productRepository.findById(productId).orElse(null);
        User user = userRepository.findById(email).orElse(null);
        if (product != null && user != null) {
            Wishlist productWishlist = wishlistRepository.getWishlistByUserEmailAndProductId(email, productId);
                if ( productWishlist != null){
                    return null;
                }
            wishlist.setProduct(product);
            wishlist.setUser(user);
            wishlistRepository.save(wishlist);
        }
        return wishlistDTOS(email);
    }

    @Override
    public List<WishlistDTO> removeProductFromWishList(int productId, String email) {
        Product product = productRepository.findById(productId).orElse(null);
        User user = userRepository.findById(email).orElse(null);
        if (product != null && user != null) {
            Wishlist productWishlist = wishlistRepository.getWishlistByUserEmailAndProductId(email, productId);
            if (productWishlist != null){
                wishlistRepository.delete(productWishlist);
            }
        }
        return wishlistDTOS(email);

    }

    @Override
    public WishlistDTO getWishlistByEmailProductID(int productId, String email) {
        return  Optional.ofNullable(wishlistRepository.getWishlistByUserEmailAndProductId(email, productId))
                .map(wishlist -> {
                    ProductDTO productDTO = modelMapper.map(wishlist.getProduct(), ProductDTO.class);
                    WishlistDTO wishlistDTO = new WishlistDTO();
                    wishlistDTO.setProduct(productDTO);
                    return wishlistDTO;
                })
                .orElseGet(WishlistDTO::new);
    }

    private List<WishlistDTO>  wishlistDTOS (String email){
        return wishlistRepository.getWishlistByUserEmail(email).stream()
                .map(wishlist -> {
                    ProductDTO productDTO = modelMapper.map(wishlist.getProduct(), ProductDTO.class);
                    WishlistDTO wishlistDTO = new WishlistDTO();
                    wishlistDTO.setProduct (productDTO);
                    wishlistDTO.setUser(wishlist.getUser());
                    return wishlistDTO;
                })
                .collect(Collectors.toList());
    }
}
