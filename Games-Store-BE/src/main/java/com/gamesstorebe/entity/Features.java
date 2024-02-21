package com.gamesstorebe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Features {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String featureName;

    @ManyToMany(mappedBy = "productsFeatures")
    @JsonBackReference
    private List<Product> productsFeatures;

}
