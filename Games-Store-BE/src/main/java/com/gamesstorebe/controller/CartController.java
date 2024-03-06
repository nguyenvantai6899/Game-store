package com.gamesstorebe.controller;

import com.gamesstorebe.customHandleError.system.Result;
import com.gamesstorebe.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping("/get-cart-by-user")
    public Result getCartByUser(@RequestParam("email") String email){
        return new Result(true, HttpStatus.OK, "Success", cartService.getCartByUserEmail(email));
    }

    @GetMapping("/get-cart-by-id")
    public Result getCartByID(@RequestParam("id") int id){
        return new Result(true, HttpStatus.OK, "Success", cartService.getCartById(id));
    }

    @PostMapping("/add-product-into-cart")
    public Result addProductToCart(@RequestParam("email") String email, @RequestParam("productId") int productId){

        return new Result(true, HttpStatus.OK, "Success",cartService.addProductIntoCart(productId, email));
    }
    @DeleteMapping("/remove-product-from-cart")
    public Result removeProductFromCart(@RequestParam("email") String email, @RequestParam("productId") int productId){
        return new Result(true, HttpStatus.OK, "Success",cartService.removeProductFromCart(productId, email));
    }
}
