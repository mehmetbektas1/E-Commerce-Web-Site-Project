package com.eticaret.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSellerProduct extends DtoBase{

	private DtoSeller seller;
	
	private DtoProduct product;
}
