package com.eticaret.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.eticaret.model.Sepet;

import jakarta.transaction.Transactional;

@Repository
public interface SepetRepository extends JpaRepository<Sepet, Long>{

	List<Sepet> findByCustomerId(Long customerId);
	
	
	@Modifying
	@Transactional
	void deleteByCustomer_Id(Long customerId);

}
