package com.eticaret.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eticaret.model.SaledProduct;

@Repository
public interface SaledProductRepository extends JpaRepository<SaledProduct, Long>{

}
