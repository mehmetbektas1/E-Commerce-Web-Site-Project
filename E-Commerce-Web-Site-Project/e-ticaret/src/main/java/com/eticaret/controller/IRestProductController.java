package com.eticaret.controller;


import java.util.List;

import com.eticaret.dto.DtoProduct;
import com.eticaret.dto.DtoProductIU;

public interface IRestProductController {

	public RootEntity<DtoProduct> saveProduct(DtoProductIU dtoProductIU);
	
	public RootEntity<List<DtoProduct>> findProduct();
}
