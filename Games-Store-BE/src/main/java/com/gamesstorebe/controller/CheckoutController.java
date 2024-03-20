package com.gamesstorebe.controller;

import com.gamesstorebe.customHandleError.system.Result;
import com.gamesstorebe.dto.CheckoutDTO;
import com.gamesstorebe.service.CheckoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
@CrossOrigin
public class CheckoutController {
    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping(value = "/save-checkout", consumes = "application/json")
    public Result saveCheckout(@RequestBody CheckoutDTO checkout){
        return new Result(true, HttpStatus.OK , "saved checkout", checkoutService.saveCheckouts(checkout));
    }

    @GetMapping("/get-checkout-by-email/{email}")
    public Result getCheckoutByEmail(@PathVariable String email){
        return new Result(true, HttpStatus.OK , "find checkout by user email", checkoutService.getCheckoutByUserEmail(email));
    }

    @PostMapping( value = "/test")
    public ResponseEntity<?> test(@RequestBody String checkout){
        return ResponseEntity.ok(checkout);
    }
}
