package com.gamesstorebe.service.impl;

import com.gamesstorebe.customHandleError.system.Result;
import com.gamesstorebe.dto.ProductDTO;
import com.gamesstorebe.entity.Features;
import com.gamesstorebe.entity.Product;
import com.gamesstorebe.entity.ProductImage;
import com.gamesstorebe.repository.FeaturesRepository;
import com.gamesstorebe.repository.ProductImageRepository;
import com.gamesstorebe.repository.ProductRepository;
import com.gamesstorebe.repository.RatingReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service("productService")
public class ProductService {

    private final ModelMapper mapper;
    private final ProductRepository productRepository;
    private final FileHandleService fileHandleService;
    private final ProductImageService productImageService;
    private final FeaturesRepository featuresRepository;
    private final ProductImageRepository productImageRepository;

    public ProductService(ProductRepository productRepository, RatingReviewRepository ratingReviewRepository, ModelMapper mapper, FileHandleService fileHandleService, ProductImageService productImageService, FeaturesRepository featuresRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.fileHandleService = fileHandleService;
        this.productImageService = productImageService;
        this.featuresRepository = featuresRepository;
        this.productImageRepository = productImageRepository;
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(product -> mapper.map(product, ProductDTO.class)).toList();
    }

    public void saveProduct(Product product, MultipartFile[] file) throws IOException {
            if (product.getId() == 0) {
                productRepository.save(product);
                //save image with product id
                List<ProductImage> productImageList = productImageService.getProductImagesByProductId(product.getId());
                for (MultipartFile file1: file){
                    ProductImage productImage = new ProductImage();
//                    productImage.setName(fileHandleService.uploadImage(file1));
                    productImage.setProduct(product);
                    productImageRepository.save(productImage);
                }
            }else {
                Product productExist = productRepository.findById(product.getId()).get();
                productExist.setName(product.getName());
                productExist.setProductsFeatures(product.getProductsFeatures());
                productExist.setStatus(product.getStatus());
                productExist.setCategories(product.getCategories());
                productExist.setDescription(product.getDescription());
                productExist.setPrice(product.getPrice());
                productExist.setDeveloper(product.getDeveloper());
                productExist.setReleaseDate(product.getReleaseDate());
                productRepository.save(productExist);

            }
        }

    public ProductDTO findProductById(int id){
        return productRepository.findById(id).map(product -> mapper.map(product, ProductDTO.class)).orElse(null);
    }
    public Result deleteProductById(Integer id) {
        try {
            Optional<Product> existingProduct  = productRepository.findById(id);
            if (existingProduct.isPresent()) {
                productRepository.deleteProductById(id);
                return new Result(true,HttpStatus.OK, "Product deleted successfully", null);
            } else {
                return new Result(true, HttpStatus.NOT_FOUND, "Product not found or could not be deleted", null);
            }
        } catch (Exception e) {
            return new Result(false,HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
    }

    public Result findProductByFeatures(int featuresID) {
        try {
            List<Product> product = productRepository.findAllByFeatures(featuresID);
            Optional<List<Product>> productOptional = Optional.ofNullable(product);
            if (productOptional.isPresent()) {
                return new Result(true, HttpStatus.OK, "The product has been successfully found", product);
            }
            return new Result(false, HttpStatus.NOT_FOUND, "The product does not exist", null);
        }
        catch (Exception e) {
            return new Result(false,HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
    }
}
