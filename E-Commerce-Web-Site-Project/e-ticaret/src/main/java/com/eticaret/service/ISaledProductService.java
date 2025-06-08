package com.eticaret.service;

import com.eticaret.dto.DtoSaledProduct;
import com.eticaret.dto.DtoSaledProductIU;

public interface ISaledProductService {

	public DtoSaledProduct buyProduct(DtoSaledProductIU dtoSaledProductIU);
}
