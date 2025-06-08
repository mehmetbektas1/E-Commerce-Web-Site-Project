package com.eticaret.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eticaret.controller.IRestSellerProductController;
import com.eticaret.controller.RestBaseController;
import com.eticaret.controller.RootEntity;
import com.eticaret.dto.DtoSellerProduct;
import com.eticaret.dto.DtoSellerProductIU;
import com.eticaret.service.ISellerProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/seller-product")
public class RestSellerProductControllerImpl extends RestBaseController implements IRestSellerProductController{

	@Autowired
	private ISellerProductService sellerProductService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoSellerProduct> saveSellerProduct(@Valid @RequestBody DtoSellerProductIU dtoSellerProductIU) {
		return ok(sellerProductService.saveSellerProduct(dtoSellerProductIU));
	}
   
	
	
	@GetMapping("/list/{ProductId}")
	@Override
	public RootEntity<Long> getByProductId(@PathVariable Long ProductId) {
		return ok(sellerProductService.getByProductId(ProductId));
	}

}
