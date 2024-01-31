package com.gamesstorebe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date paymentDate;
    private String paymentMethod;
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}