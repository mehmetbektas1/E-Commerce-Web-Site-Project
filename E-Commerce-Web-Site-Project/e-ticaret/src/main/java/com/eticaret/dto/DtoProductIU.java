package com.eticaret.dto;

import java.math.BigDecimal;

import com.eticaret.enums.ProductStatusType;
import com.eticaret.enums.CurrencyType;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoProductIU {

	@NotNull
	private String productCategory;
	
	@NotNull
	private String productDiscount;
	
	@NotNull
	private String productName;
	
	@NotNull
	private String productDescription;
	
	@NotNull
	private Integer productionYear;
	
	@NotNull
	private BigDecimal price;
	
	@NotNull
	private CurrencyType currencyType;
	
	@NotNull
	private BigDecimal damagePrice;
	
	@NotNull
	private ProductStatusType productStatusType;

}
