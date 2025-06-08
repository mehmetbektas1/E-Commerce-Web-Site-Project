package com.eticaret.controller;

import com.eticaret.dto.DtoAccount;
import com.eticaret.dto.DtoAccountIU;

public interface IRestAccountController {

	public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);
}
