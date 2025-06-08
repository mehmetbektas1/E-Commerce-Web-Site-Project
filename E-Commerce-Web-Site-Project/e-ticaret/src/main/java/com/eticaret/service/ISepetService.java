package com.eticaret.service;


import java.util.List;

import com.eticaret.dto.DtoSeller;
import com.eticaret.dto.DtoSellerIU;
import com.eticaret.dto.DtoSepet;
import com.eticaret.dto.DtoSepetIU;
import com.eticaret.model.Sepet;

public interface ISepetService {
	
	 public List<DtoSepet> getSepetByCustomerId(Long customerId);
	 
	 public DtoSepet saveSepet(DtoSepetIU dtoSepetIU);
	
	 public void deleteSepet(Long id);
	

}
