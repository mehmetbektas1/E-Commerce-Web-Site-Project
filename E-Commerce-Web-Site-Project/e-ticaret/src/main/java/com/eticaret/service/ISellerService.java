package com.eticaret.service;

import com.eticaret.dto.DtoSeller;
import com.eticaret.dto.DtoSellerIU;

public interface ISellerService {

	public DtoSeller saveSeller(DtoSellerIU dtoSellerIU);
}
