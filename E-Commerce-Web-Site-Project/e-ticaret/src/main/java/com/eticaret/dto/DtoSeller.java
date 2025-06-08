package com.eticaret.dto;

import com.eticaret.model.User;

import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSeller extends DtoBase {

	private String firstName;

	private String lastName;

	private DtoAddress address;
	
	private DtoUser user;
}
