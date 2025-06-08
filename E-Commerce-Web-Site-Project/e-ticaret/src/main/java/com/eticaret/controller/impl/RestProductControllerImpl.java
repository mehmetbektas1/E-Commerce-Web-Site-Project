package com.eticaret.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.eticaret.controller.IRestProductController;
import com.eticaret.controller.RestBaseController;
import com.eticaret.controller.RootEntity;
import com.eticaret.dto.CurrencyRatesResponse;
import com.eticaret.dto.DtoProduct;
import com.eticaret.dto.DtoProductIU;
import com.eticaret.service.IProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/product")
public class RestProductControllerImpl extends RestBaseController implements IRestProductController{

	@Autowired
	private IProductService productService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoProduct> saveProduct(@Valid @RequestBody DtoProductIU dtoProductIU) {
		return ok(productService.saveProduct(dtoProductIU));
	}
	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoProduct>> findProduct() {
	    List<DtoProduct> products = productService.findProduct(); // dikkat: çoğul!
	    return ok(products); // ok metodu yukarıdaki gibi olmalı
	}


}
