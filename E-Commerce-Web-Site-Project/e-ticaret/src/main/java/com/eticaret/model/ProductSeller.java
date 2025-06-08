package com.eticaret.model;

import java.math.BigDecimal;

import com.eticaret.enums.ProductStatusType;
import com.eticaret.enums.CurrencyType;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_seller",
uniqueConstraints = {@UniqueConstraint(columnNames = {"seller_id" , "product_id"},name = "uq_seller_product")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSeller extends BaseEntity{

	@ManyToOne
	private Seller seller;
	
	@ManyToOne
	private Product product;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
