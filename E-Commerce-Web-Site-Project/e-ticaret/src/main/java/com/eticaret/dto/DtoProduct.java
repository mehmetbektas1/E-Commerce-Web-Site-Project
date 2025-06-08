package com.eticaret.dto;

import java.math.BigDecimal;

import com.eticaret.enums.ProductStatusType;
import com.eticaret.enums.CurrencyType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoProduct extends DtoBase{

	private String productCategory;

	private String productDiscount;

	private String productName;
	
	private String productDescription;

	private Integer productionYear;

	private BigDecimal price;

	private CurrencyType currencyType;

	private BigDecimal damagePrice;

	private ProductStatusType productStatusType;
}
