package com.eticaret.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoSepetIU{

	
	@NotNull
	private Long customerId;
	
	@NotNull
	private Long productId;
}
