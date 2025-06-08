package com.eticaret.controller;

import com.eticaret.dto.DtoSellerProduct;
import com.eticaret.dto.DtoSellerProductIU;

public interface IRestSellerProductController {

	public RootEntity<DtoSellerProduct> saveSellerProduct(DtoSellerProductIU dtoSellerProductIU);
	
	public RootEntity<Long> getByProductId(Long ProductId);
}
