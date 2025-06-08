package com.eticaret.controller;

import com.eticaret.dto.DtoSeller;
import com.eticaret.dto.DtoSellerIU;

public interface IRestSellerController {

	public RootEntity<DtoSeller> saveSeller(DtoSellerIU dtoSellerIU);
}
