package com.example.demominitest2.repository;

import com.example.demominitest2.model.Category;
import com.example.demominitest2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
