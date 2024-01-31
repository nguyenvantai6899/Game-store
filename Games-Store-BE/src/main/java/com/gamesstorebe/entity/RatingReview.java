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
public class RatingReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Double rating;
    private String review;
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product products;
}
