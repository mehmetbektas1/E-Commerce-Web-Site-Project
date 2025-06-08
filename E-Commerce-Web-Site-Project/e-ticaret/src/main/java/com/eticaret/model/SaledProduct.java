package com.eticaret.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "saled_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaledProduct  extends BaseEntity{

	@ManyToOne
	private Seller seller;
	
	@ManyToMany
	private List<Product> products;
	
	@ManyToOne
	private Customer customer;
}
