package com.eticaret.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSellerProductIU {

	@NotNull
	private Long sellerId;
	
	@NotNull
	private Long productId;
}
