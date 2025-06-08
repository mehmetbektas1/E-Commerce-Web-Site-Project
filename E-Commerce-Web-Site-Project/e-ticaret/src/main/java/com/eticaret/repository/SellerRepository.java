package com.eticaret.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eticaret.model.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long>{

}
