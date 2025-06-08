package com.eticaret.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSaledProductIU {

	@NotNull
	private Long customerId;
	
	@NotNull
	private Long sellerId;
	
}
