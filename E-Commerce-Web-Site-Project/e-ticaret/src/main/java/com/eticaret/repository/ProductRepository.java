package com.eticaret.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eticaret.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
