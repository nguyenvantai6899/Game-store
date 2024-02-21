package com.gamesstorebe.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamesstorebe.customHandleError.system.Result;
import com.gamesstorebe.entity.Product;
import com.gamesstorebe.service.ProductDiscountService;
import com.gamesstorebe.service.impl.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final ProductDiscountService productDiscountService;
    public ProductController(ProductService productService, ProductDiscountService productDiscountService) {
        this.productService = productService;
        this.productDiscountService = productDiscountService;
    }

    @GetMapping("/")
    public Result getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/get-product-by-id/{productId}")
    public Result getProductByProductId(@PathVariable (name = "productId") Integer productId) {
        return productService.findProductById(productId);
    }

    @PostMapping("save-product")
    public Result saveProduct (@RequestParam String product, @RequestParam("image") MultipartFile[] file) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Product productEntity = mapper.readValue(product, Product.class);
        try {
            if (productEntity.getId() == 0) {
                productService.saveProduct(productEntity, file);
                return new Result(true, HttpStatus.OK, "The product has been successfully created", productEntity);
            }else {
                productService.saveProduct(productEntity, file);
                return new Result(true, HttpStatus.OK,"The product has been edit successfully", productEntity);
            }
        }catch (Exception e) {
            return new Result(false,HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
    }

    @GetMapping("/get-product-by-featured/{featuredId}")
    public Result getProductByFeatured(@PathVariable (name = "featuredId") int featuredId) {
        return productService.findProductByFeatures(featuredId);
    }

    @GetMapping("/get-products-discount")
    public Result getProductsDiscount(){
        return new Result(true, HttpStatus.OK, "get products discount", productDiscountService.getProducts());
    }
}
