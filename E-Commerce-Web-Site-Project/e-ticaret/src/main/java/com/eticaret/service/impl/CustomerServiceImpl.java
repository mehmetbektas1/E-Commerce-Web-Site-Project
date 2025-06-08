package com.eticaret.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eticaret.dto.DtoAccount;
import com.eticaret.dto.DtoAddress;
import com.eticaret.dto.DtoCustomer;
import com.eticaret.dto.DtoCustomerIU;
import com.eticaret.dto.DtoUser;
import com.eticaret.exception.BaseException;
import com.eticaret.exception.ErrorMessage;
import com.eticaret.exception.MessageType;
import com.eticaret.model.Account;
import com.eticaret.model.Address;
import com.eticaret.model.Customer;
import com.eticaret.model.User;
import com.eticaret.repository.AccountRepository;
import com.eticaret.repository.AddressRepository;
import com.eticaret.repository.CustomerRepository;
import com.eticaret.repository.UserRepository;
import com.eticaret.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	private Customer createCustomer(DtoCustomerIU dtoCustomerIU) {
		Optional<Address> optAddress = addressRepository.findById(dtoCustomerIU.getAddressId());
		if(optAddress.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAddressId().toString()));
		}
		
		Optional<Account> optAccount = accountRepository.findById(dtoCustomerIU.getAccountId());
		if(optAccount.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAccountId().toString()));
		}
		
		Optional<User> optUser = userRepository.findById(dtoCustomerIU.getUserId());
		if(optUser.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getUserId().toString()));
		}
		
		
		Customer customer = new Customer();
		customer.setCreateTime(new Date());
		BeanUtils.copyProperties(dtoCustomerIU, customer);
		
		customer.setAddress(optAddress.get());
		customer.setAccount(optAccount.get());
		customer.setUser(optUser.get());
		
		return customer;
	}
	
	@Override
	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
		DtoCustomer dtoCustomer = new DtoCustomer();
		DtoAddress dtoAddress = new DtoAddress();
		DtoAccount dtoAccount = new DtoAccount();
		DtoUser    dtoUser=new DtoUser();
		
		Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerIU));
		
		BeanUtils.copyProperties(savedCustomer, dtoCustomer);
		BeanUtils.copyProperties(savedCustomer.getAddress(), dtoAddress);
		BeanUtils.copyProperties(savedCustomer.getAccount(), dtoAccount);
		BeanUtils.copyProperties(savedCustomer.getUser(), dtoUser);
		
		dtoCustomer.setAddress(dtoAddress);
		dtoCustomer.setAccount(dtoAccount);
		dtoCustomer.setUser(dtoUser);
		return dtoCustomer;
	}

	
}
