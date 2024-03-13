package com.gamesstorebe.controller;

import com.gamesstorebe.customHandleError.system.Result;
import com.gamesstorebe.entity.Wishlist;
import com.gamesstorebe.service.WishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "wishlist")
@RestController
public class WishListController {
    private final WishlistService wishlistService;

    public WishListController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("get-wishlist-by-email")
    public Result getWishlistByEmail (@RequestParam("email") String email) {
        return new Result(true, HttpStatus.OK, "Get all product in Wishlist", wishlistService.getWishlistByUser(email));
    }

    @PutMapping("add-product-to-wishlist")
    public Result addProductToWishlist(@RequestParam("email") String email,@RequestParam("productId") int productId) {
        return new Result(true, HttpStatus.OK, "Add product to Wishlist", wishlistService.addProductIntoWishList(productId, email));
    }
    @DeleteMapping("delete-product-from-wishlist")
    public Result deleteProductFromWishList(@RequestParam("email") String email,@RequestParam("productId") int productId) {
        return new Result(true, HttpStatus.OK, "Add product to Wishlist", wishlistService.removeProductFromWishList(productId, email));
    }

    @GetMapping("get-wishlist-by-email-productID")
    public Result getWishlistByEmailProductID (@RequestParam("email") String email,@RequestParam("productId") int productId) {
        return new Result(true, HttpStatus.OK, "Get all product in Wishlist", wishlistService.getWishlistByEmailProductID(productId, email));
    }
}
