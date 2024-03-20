package com.gamesstorebe.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gamesstorebe.entity.User;
import lombok.*;

import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutDTO {
    private int id;
    private Date paymentDate;
    private String paymentMethod;
    private Double amount;
    private User user;
    private List<ProductDTO> productCheckout;
}
