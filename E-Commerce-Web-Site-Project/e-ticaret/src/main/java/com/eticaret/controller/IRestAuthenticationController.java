package com.eticaret.controller;

import com.eticaret.dto.AuthRequest;
import com.eticaret.dto.AuthResponse;
import com.eticaret.dto.DtoUser;
import com.eticaret.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {

	public RootEntity<DtoUser> register(AuthRequest input);
	
	public RootEntity<AuthResponse> authenticate(AuthRequest input);
	
	public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);
}
