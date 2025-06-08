package com.eticaret.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eticaret.controller.IRestSepetController;
import com.eticaret.controller.RestBaseController;
import com.eticaret.controller.RootEntity;
import com.eticaret.dto.DtoSeller;
import com.eticaret.dto.DtoSellerIU;
import com.eticaret.dto.DtoSepet;
import com.eticaret.dto.DtoSepetIU;
import com.eticaret.service.ISepetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/sepet")
public class RestSepetControllerImpl extends RestBaseController implements IRestSepetController {
   
	
	@Autowired
    private ISepetService sepetService;
	
	
	@Override
	@GetMapping("/list/{customerId}")
	public RootEntity<List<DtoSepet>> getSepetByCustomerId(@PathVariable Long customerId) {
		List<DtoSepet> dtoList = sepetService.getSepetByCustomerId(customerId);
		return ok(dtoList);
	}
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoSepet> saveSepet(@Valid @RequestBody DtoSepetIU dtoSepetIU) {
		return ok(sepetService.saveSepet(dtoSepetIU));
	}
	@DeleteMapping(path = "/delete/{id}")
	@Override
	public void deleteSepet(@PathVariable(name = "id") Long id) {
		sepetService.deleteSepet(id);
	}
	
}
