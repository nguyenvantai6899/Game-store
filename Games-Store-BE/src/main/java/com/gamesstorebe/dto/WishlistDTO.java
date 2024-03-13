package com.gamesstorebe.dto;

import com.gamesstorebe.entity.User;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WishlistDTO {
    private int id;
    private ProductDTO product;
    private User user;
}
