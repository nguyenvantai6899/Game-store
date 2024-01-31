package com.gamesstorebe.repository;

import com.gamesstorebe.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories, Integer> {
}
