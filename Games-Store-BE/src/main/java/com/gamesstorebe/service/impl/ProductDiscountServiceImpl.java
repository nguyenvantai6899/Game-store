package com.gamesstorebe.service.impl;

import com.gamesstorebe.entity.ProductDiscount;
import com.gamesstorebe.repository.ProductDiscountRepository;
import com.gamesstorebe.service.ProductDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDiscountServiceImpl implements ProductDiscountService {

    private final ProductDiscountRepository productDiscountRepository;

    public ProductDiscountServiceImpl(ProductDiscountRepository productDiscountRepository) {
        this.productDiscountRepository = productDiscountRepository;
    }

    @Override
    public List<ProductDiscount> getProducts() {
        return productDiscountRepository.getProducts();
    }
}
