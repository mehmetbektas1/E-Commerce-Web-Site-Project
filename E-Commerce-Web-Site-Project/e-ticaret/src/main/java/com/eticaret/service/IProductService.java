package com.eticaret.service;

import java.util.List;

import com.eticaret.dto.DtoProduct;
import com.eticaret.dto.DtoProductIU;

public interface IProductService {

	public DtoProduct saveProduct(DtoProductIU dtoProductIU);
	
	public List<DtoProduct> findProduct();
}
