package com.gamesstorebe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Checkout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date paymentDate;
    private String paymentMethod;
    private Double amount;
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "product_checkout",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "checkout_id")
    )
    private List<Product> productCheckout;
}