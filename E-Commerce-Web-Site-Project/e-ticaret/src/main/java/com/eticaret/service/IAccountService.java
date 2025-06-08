package com.eticaret.service;

import com.eticaret.dto.DtoAccount;
import com.eticaret.dto.DtoAccountIU;

public interface IAccountService {

	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
}
