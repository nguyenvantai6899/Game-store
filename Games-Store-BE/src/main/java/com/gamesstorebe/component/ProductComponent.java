package com.gamesstorebe.component;

import com.gamesstorebe.dto.ProductDTO;
import com.gamesstorebe.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductComponent {
    private final ModelMapper modelMapper;

    public ProductComponent(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProductDTO convertToDTO(Product department) {
        return modelMapper.map(department, ProductDTO.class);
    }
    public List<ProductDTO> convertToDTOList(List<Product> departments) {
        return departments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
