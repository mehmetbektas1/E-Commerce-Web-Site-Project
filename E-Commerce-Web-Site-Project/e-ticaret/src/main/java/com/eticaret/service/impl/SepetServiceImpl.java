package com.eticaret.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.eticaret.dto.DtoAccount;
import com.eticaret.dto.DtoAddress;
import com.eticaret.dto.DtoCustomer;
import com.eticaret.dto.DtoProduct;
import com.eticaret.dto.DtoSeller;
import com.eticaret.dto.DtoSellerIU;
import com.eticaret.dto.DtoSepet;
import com.eticaret.dto.DtoSepetIU;
import com.eticaret.dto.DtoUser;
import com.eticaret.exception.BaseException;
import com.eticaret.exception.ErrorMessage;
import com.eticaret.exception.MessageType;
import com.eticaret.model.Address;
import com.eticaret.model.Customer;
import com.eticaret.model.Product;
import com.eticaret.model.Seller;
import com.eticaret.model.Sepet;
import com.eticaret.model.User;
import com.eticaret.repository.CustomerRepository;
import com.eticaret.repository.ProductRepository;
import com.eticaret.repository.SepetRepository;
import com.eticaret.service.ISepetService;

@Service
public class SepetServiceImpl implements ISepetService{

	
	@Autowired
    private SepetRepository sepetRepository;
	
	@Autowired
    private CustomerRepository costumerRepository;
	
	@Autowired
    private ProductRepository productRepository;

	
	
	 public List<DtoSepet> getSepetByCustomerId(Long customerId) {
	        List<Sepet> sepetList = sepetRepository.findByCustomerId(customerId);
	        List<DtoSepet> dtoList = new ArrayList<>();
	        for (Sepet sepet : sepetList) {
	            DtoSepet dto = new DtoSepet();
	            BeanUtils.copyProperties(sepet, dto);
	            // Product kopyalama
	            DtoProduct dtoProduct = new DtoProduct();
	            BeanUtils.copyProperties(sepet.getProduct(), dtoProduct);
	            dto.setProduct(dtoProduct);

	            // Customer kopyalama
	            DtoCustomer dtoCustomer = new DtoCustomer();
	            BeanUtils.copyProperties(sepet.getCustomer(), dtoCustomer);
	            dto.setCustomer(dtoCustomer);

	            dtoList.add(dto);
	        }

	        return dtoList;
	    }



	 private Sepet createSepet(DtoSepetIU dtoSepetIU) {

			Optional<Customer> optCustomer = costumerRepository.findById(dtoSepetIU.getCustomerId());
			if (optCustomer.isEmpty()) {
				throw new BaseException(
						new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSepetIU.getCustomerId().toString()));
			}
			
			Optional<Product> optProduct = productRepository.findById(dtoSepetIU.getProductId());
			if(optProduct.isEmpty()) {
				throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSepetIU.getProductId().toString()));
			}

			Sepet sepet = new Sepet();
			sepet.setCreateTime(new Date());

			BeanUtils.copyProperties(dtoSepetIU, sepet);
			sepet.setCustomer(optCustomer.get());
			sepet.setProduct(optProduct.get());

			return sepet;

		}

		@Override
		public DtoSepet saveSepet(DtoSepetIU dtoSepetIU) {
			DtoSepet dtoSepet = new DtoSepet();

		    Sepet savedSepet = sepetRepository.save(createSepet(dtoSepetIU));

		    // Ana entity'den DTO'ya temel özellikleri kopyala
		    BeanUtils.copyProperties(savedSepet, dtoSepet);

		    // Customer DTO'su oluştur
		    DtoCustomer dtoCustomer = new DtoCustomer();
		    BeanUtils.copyProperties(savedSepet.getCustomer(), dtoCustomer);

		    // User DTO'su oluştur
		    DtoUser dtoUser = new DtoUser();
		    BeanUtils.copyProperties(savedSepet.getCustomer().getUser(), dtoUser);
		    dtoCustomer.setUser(dtoUser);

		    // Account DTO'su oluştur
		    DtoAccount dtoAccount = new DtoAccount();
		    BeanUtils.copyProperties(savedSepet.getCustomer().getAccount(), dtoAccount);
		    dtoCustomer.setAccount(dtoAccount);

		    // Address DTO'su oluştur
		    DtoAddress dtoAddress = new DtoAddress();
		    BeanUtils.copyProperties(savedSepet.getCustomer().getAddress(), dtoAddress);
		    dtoCustomer.setAddress(dtoAddress);

		    // Ürünü kopyala
		    DtoProduct dtoProduct = new DtoProduct();
		    BeanUtils.copyProperties(savedSepet.getProduct(), dtoProduct);

		    // Customer ve Product DTO'larını Sepet DTO'suna ekle
		    dtoSepet.setCustomer(dtoCustomer);
		    dtoSepet.setProduct(dtoProduct);

		    return dtoSepet;
		}
		
		
		
		@Override
		public void deleteSepet(Long id) {
			Optional<Sepet> optional = sepetRepository.findById(id);
			if (optional.isPresent()) {
				sepetRepository.delete(optional.get());
			}
		}
	 
	 public BigDecimal calculateTotalPrice(Long customerId) {
		 List<Sepet> sepetList = sepetRepository.findByCustomerId(customerId);
		 BigDecimal totalPrice = BigDecimal.ZERO;
		 
		 for (Sepet sepet : sepetList) {
		        Product product = sepet.getProduct(); 
		        if (product != null && product.getPrice() != null) {
		            totalPrice = totalPrice.add(product.getPrice());
		        }
		    }

		    return totalPrice;
		 
		 
	 }
	 
	 
	 
	 
	
	
	
	
}
