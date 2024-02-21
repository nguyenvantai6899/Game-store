package com.gamesstorebe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Double percentDiscount;
    private Date startDate;
    private Date endDate;
    private Boolean status;

    @OneToMany(mappedBy = "discount")
    @JsonBackReference
    private List<ProductDiscount> productDiscounts;

}
