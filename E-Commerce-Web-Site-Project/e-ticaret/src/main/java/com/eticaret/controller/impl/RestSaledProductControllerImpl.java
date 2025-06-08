package com.eticaret.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eticaret.controller.IRestSaledProductController;
import com.eticaret.controller.RestBaseController;
import com.eticaret.controller.RootEntity;
import com.eticaret.dto.DtoSaledProduct;
import com.eticaret.dto.DtoSaledProductIU;
import com.eticaret.service.ISaledProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/saled-product")
public class RestSaledProductControllerImpl extends RestBaseController implements IRestSaledProductController{

	@Autowired
	private ISaledProductService saledProductService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoSaledProduct> buyProduct(@Valid @RequestBody DtoSaledProductIU dtoSaledProductIU) {
		return ok(saledProductService.buyProduct(dtoSaledProductIU));
	}

}
