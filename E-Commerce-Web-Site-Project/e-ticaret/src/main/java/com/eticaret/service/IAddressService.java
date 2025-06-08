package com.eticaret.service;

import com.eticaret.dto.DtoAddress;
import com.eticaret.dto.DtoAddressIU;

public interface IAddressService {

	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);
}
