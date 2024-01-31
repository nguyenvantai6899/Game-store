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
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Double percentDiscount;
    private Date startDate;
    private Date endDate;
    private Boolean status;

}
