package com.eticaret.service;

import com.eticaret.dto.DtoCustomer;
import com.eticaret.dto.DtoCustomerIU;

public interface ICustomerService {

	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
}
