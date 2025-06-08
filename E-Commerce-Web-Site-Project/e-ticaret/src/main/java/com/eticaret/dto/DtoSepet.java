package com.eticaret.dto;

import com.eticaret.model.User;

import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSepet extends DtoBase{

	private DtoCustomer customer;
	
	private DtoProduct product;
}