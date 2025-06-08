package com.eticaret.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eticaret.controller.IRestSellerController;
import com.eticaret.controller.RestBaseController;
import com.eticaret.controller.RootEntity;
import com.eticaret.dto.DtoSeller;
import com.eticaret.dto.DtoSellerIU;
import com.eticaret.service.ISellerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/seller")
public class RestSellerControllerImpl extends RestBaseController implements IRestSellerController{

	@Autowired
	private ISellerService sellerService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoSeller> saveSeller(@Valid @RequestBody DtoSellerIU dtoSellerIU) {
		return ok(sellerService.saveSeller(dtoSellerIU));
	}

}
