package com.gamesstorebe.service;

import com.gamesstorebe.dto.CheckoutDTO;
import com.gamesstorebe.entity.Checkout;

import java.util.List;

public interface CheckoutService {
    List<CheckoutDTO> getCheckoutByUserEmail (String userEmail);
    CheckoutDTO saveCheckouts (CheckoutDTO checkout);
}
