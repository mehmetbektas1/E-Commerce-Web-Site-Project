package com.eticaret.service;

import com.eticaret.dto.DtoSellerProduct;
import com.eticaret.dto.DtoSellerProductIU;

public interface ISellerProductService {

	public DtoSellerProduct saveSellerProduct(DtoSellerProductIU dtoSellerProductIU);
	
	public Long getByProductId(Long ProductId);
}
