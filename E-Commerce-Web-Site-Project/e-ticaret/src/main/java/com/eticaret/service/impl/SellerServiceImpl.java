package com.eticaret.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eticaret.dto.DtoAddress;
import com.eticaret.dto.DtoSeller;
import com.eticaret.dto.DtoSellerIU;
import com.eticaret.dto.DtoUser;
import com.eticaret.exception.BaseException;
import com.eticaret.exception.ErrorMessage;
import com.eticaret.exception.MessageType;
import com.eticaret.model.Address;
import com.eticaret.model.Seller;
import com.eticaret.model.User;
import com.eticaret.repository.AddressRepository;
import com.eticaret.repository.SellerRepository;
import com.eticaret.repository.UserRepository;
import com.eticaret.service.ISellerService;

@Service
public class SellerServiceImpl implements ISellerService {

	@Autowired
	private SellerRepository sellerRepository;

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;

	private Seller createSeller(DtoSellerIU dtoSellerIU) {

		Optional<Address> optAddress = addressRepository.findById(dtoSellerIU.getAddressId());
		if (optAddress.isEmpty()) {
			throw new BaseException(
					new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSellerIU.getAddressId().toString()));
		}
		
		Optional<User> optUser = userRepository.findById(dtoSellerIU.getUserId());
		if(optUser.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSellerIU.getUserId().toString()));
		}

		Seller seller = new Seller();
		seller.setCreateTime(new Date());

		BeanUtils.copyProperties(dtoSellerIU, seller);
		seller.setAddress(optAddress.get());
		seller.setUser(optUser.get());

		return seller;

	}

	@Override
	public DtoSeller saveSeller(DtoSellerIU dtoSellerIU) {
		DtoSeller dtoSeller = new DtoSeller();
		DtoAddress dtoAddress = new DtoAddress();
		DtoUser dtoUser = new DtoUser();
		Seller savedSeller = sellerRepository.save(createSeller(dtoSellerIU));

		BeanUtils.copyProperties(savedSeller, dtoSeller);
		BeanUtils.copyProperties(savedSeller.getAddress(), dtoAddress);
		BeanUtils.copyProperties(savedSeller.getUser(), dtoUser);
		dtoSeller.setAddress(dtoAddress);
		dtoSeller.setUser(dtoUser);
		return dtoSeller;
	}

}
