package com.eticaret.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.eticaret.dto.DtoSeller;
import com.eticaret.dto.DtoSellerIU;
import com.eticaret.dto.DtoSepet;
import com.eticaret.dto.DtoSepetIU;

public interface IRestSepetController {

	public RootEntity<List<DtoSepet>> getSepetByCustomerId(@PathVariable Long customerId);
	
	public RootEntity<DtoSepet> saveSepet(DtoSepetIU dtoSepetIU);
	
	public void deleteSepet(Long id);
}
