package com.gamesstorebe.dto;

import com.gamesstorebe.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private int id;
    private List<ProductDTO> products;

}
