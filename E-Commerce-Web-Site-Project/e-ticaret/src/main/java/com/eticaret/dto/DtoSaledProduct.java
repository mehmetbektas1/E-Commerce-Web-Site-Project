package com.eticaret.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSaledProduct extends DtoBase{

	private DtoCustomer customer;
	
	private DtoSeller seller;
	
	private List<DtoProduct> product;
}
