package com.gamesstorebe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantityProduct;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product products;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transactions;
}
