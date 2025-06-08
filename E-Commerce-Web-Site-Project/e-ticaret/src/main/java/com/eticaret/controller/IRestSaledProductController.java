package com.eticaret.controller;

import com.eticaret.dto.DtoSaledProduct;
import com.eticaret.dto.DtoSaledProductIU;

public interface IRestSaledProductController {

	public RootEntity<DtoSaledProduct> buyProduct(DtoSaledProductIU dtoSaledProductIU);
}
