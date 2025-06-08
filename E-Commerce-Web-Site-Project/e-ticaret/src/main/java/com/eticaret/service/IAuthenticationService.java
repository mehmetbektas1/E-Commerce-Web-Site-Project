package com.eticaret.service;

import com.eticaret.dto.AuthRequest;
import com.eticaret.dto.AuthResponse;
import com.eticaret.dto.DtoUser;
import com.eticaret.dto.RefreshTokenRequest;

public interface IAuthenticationService {

	public DtoUser register(AuthRequest input);
	
	public AuthResponse authenticate(AuthRequest input);
	
	public AuthResponse refreshToken(RefreshTokenRequest input);
}
