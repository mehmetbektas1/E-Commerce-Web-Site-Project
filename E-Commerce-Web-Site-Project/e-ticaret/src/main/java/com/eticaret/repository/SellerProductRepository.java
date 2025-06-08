package com.eticaret.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eticaret.model.ProductSeller;

@Repository
public interface SellerProductRepository extends JpaRepository<ProductSeller, Long>{
	
	Optional<ProductSeller> findByProduct_Id(Long productId);

}
