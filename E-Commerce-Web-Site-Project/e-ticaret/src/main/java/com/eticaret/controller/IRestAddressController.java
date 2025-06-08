package com.eticaret.controller;

import com.eticaret.dto.DtoAddress;
import com.eticaret.dto.DtoAddressIU;

public interface IRestAddressController {

	public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
}
