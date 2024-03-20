package com.gamesstorebe.repository;

import com.gamesstorebe.entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckoutRepository extends JpaRepository<Checkout, Integer> {
    List<Checkout> findCheckoutByUserEmail (String userEmail);
}
