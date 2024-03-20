package com.gamesstorebe.service.impl;

import com.gamesstorebe.dto.CheckoutDTO;
import com.gamesstorebe.entity.Checkout;
import com.gamesstorebe.entity.Product;
import com.gamesstorebe.repository.CheckoutRepository;
import com.gamesstorebe.service.CheckoutService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CheckoutService")
public class CheckoutServiceImpl implements CheckoutService {

    private final CheckoutRepository checkoutRepository;
    private final ModelMapper modelMapper;

    public CheckoutServiceImpl(CheckoutRepository checkoutRepository, ModelMapper modelMapper) {
        this.checkoutRepository = checkoutRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CheckoutDTO> getCheckoutByUserEmail(String userEmail) {
        return checkoutRepository.findCheckoutByUserEmail(userEmail).stream().map( checkout ->
                modelMapper.map(checkout, CheckoutDTO.class)).toList();
    }

    @Override
    public CheckoutDTO saveCheckouts(CheckoutDTO checkoutDTO) {
        if (checkoutDTO.getProductCheckout() != null) {
            Checkout checkout = new Checkout();
            checkout.setProductCheckout(checkoutDTO.getProductCheckout().stream()
                    .map( ck -> modelMapper.map(ck, Product.class)).toList());
            checkout.setUser(checkoutDTO.getUser());
            checkout.setPaymentDate(checkoutDTO.getPaymentDate());
            checkout.setPaymentMethod(checkoutDTO.getPaymentMethod());
            checkout.setAmount(checkoutDTO.getAmount());
            checkoutRepository.save(checkout);
        }
        return null;

    }
}
