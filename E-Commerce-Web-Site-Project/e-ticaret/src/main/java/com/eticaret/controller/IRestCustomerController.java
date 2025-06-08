package com.eticaret.controller;

import com.eticaret.dto.DtoCustomer;
import com.eticaret.dto.DtoCustomerIU;

public interface IRestCustomerController {

	public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);
}
