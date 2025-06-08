package com.eticaret.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sepet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sepet extends BaseEntity{

	@ManyToOne
	private Customer customer;
	
	@ManyToOne
	private Product product;
	

	
	
	
	
	
	
	
	
	
}

