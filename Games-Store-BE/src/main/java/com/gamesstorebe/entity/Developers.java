package com.gamesstorebe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Developers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String  developerName;


}
