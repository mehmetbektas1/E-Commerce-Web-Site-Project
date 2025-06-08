package com.eticaret.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eticaret.dto.CurrencyRatesResponse;
import com.eticaret.dto.DtoProduct;
import com.eticaret.dto.DtoCustomer;
import com.eticaret.dto.DtoSeller;
import com.eticaret.dto.DtoSepet;
import com.eticaret.dto.DtoSaledProduct;
import com.eticaret.dto.DtoSaledProductIU;
import com.eticaret.enums.ProductStatusType;
import com.eticaret.exception.BaseException;
import com.eticaret.exception.ErrorMessage;
import com.eticaret.exception.MessageType;
import com.eticaret.model.Product;
import com.eticaret.model.Customer;
import com.eticaret.model.SaledProduct;
import com.eticaret.model.Sepet;
import com.eticaret.repository.ProductRepository;
import com.eticaret.repository.CustomerRepository;
import com.eticaret.repository.SellerRepository;
import com.eticaret.repository.SepetRepository;
import com.eticaret.repository.SaledProductRepository;
import com.eticaret.service.ICurrencyRatesService;
import com.eticaret.service.ISaledProductService;
import com.eticaret.utils.DateUtils;

@Service
public class SaledProductServiceImpl implements ISaledProductService {
	
	@Autowired
	private SaledProductRepository saledProductRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private SellerRepository sellerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ICurrencyRatesService currencyRatesService;
	
	@Autowired
	private SepetServiceImpl sepetService;
	
	@Autowired
	private SepetRepository sepetRepository;
	

	public BigDecimal convertCustomerAmountToUSD(Customer customer) {

		CurrencyRatesResponse currencyRatesResponse = currencyRatesService
				.getCurrencyRates("11-10-2024","11-10-2024");
		BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

		BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);

		return customerUSDAmount;
	}
	
	public boolean checkProductStatus(Long customerId) {
		
		List<Sepet> sepet =sepetRepository.findByCustomerId(customerId);
		
		for (Sepet s : sepet) {

			if(s.getProduct().getProductStatusType().name().equals(ProductStatusType.SALED.name())) {
				return false;
			}
		}
		return true;
	}
	
	public BigDecimal remaningCustomerAmount(Customer customer) {
		BigDecimal customerUSDAmount = convertCustomerAmountToUSD(customer);
		BigDecimal remaningCustomerUSDAmount = customerUSDAmount.subtract(sepetService.calculateTotalPrice(customer.getId()));
		
		CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates("11-10-2024","11-10-2024");
		BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
		//2000   - 34.15
		
		return  remaningCustomerUSDAmount.multiply(usd);
	}

	public boolean checkAmount(DtoSaledProductIU dtoSaledProductIU) {

		Optional<Customer> optCustomer = customerRepository.findById(dtoSaledProductIU.getCustomerId());
		if (optCustomer.isEmpty()) {
			throw new BaseException(
					new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledProductIU.getCustomerId().toString()));
		}

		List<DtoSepet> optSepet = sepetService.getSepetByCustomerId(dtoSaledProductIU.getCustomerId());
		if (optSepet.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledProductIU.getCustomerId().toString()));
		}

		BigDecimal customerUSDAmount = convertCustomerAmountToUSD(optCustomer.get());

		// 37.000 35.000 = 0 1 -1     =2000
		if (customerUSDAmount.compareTo(sepetService.calculateTotalPrice(optCustomer.get().getId())) == 0
				|| customerUSDAmount.compareTo(sepetService.calculateTotalPrice(optCustomer.get().getId())) > 0) {
			return true;
		}
		return false;

	}
	
	private SaledProduct createSaledProduct(DtoSaledProductIU dtoSaledProductIU) {
		SaledProduct saledProduct = new SaledProduct();
		saledProduct.setCreateTime(new Date());
		
		saledProduct.setCustomer(customerRepository.findById(dtoSaledProductIU.getCustomerId()).orElse(null));
		saledProduct.setSeller(sellerRepository.findById(dtoSaledProductIU.getSellerId()).orElse(null));
		List<Sepet> sepet =sepetRepository.findByCustomerId(dtoSaledProductIU.getCustomerId());
		List<Product> allProducts = new ArrayList<>();
		for (Sepet s : sepet) {
		    allProducts.add(s.getProduct()); // getProducts: Sepet içindeki ürün listesi
		}

		saledProduct.setProducts(allProducts);
		return saledProduct;
	}

	@Override
	public DtoSaledProduct buyProduct(DtoSaledProductIU dtoSaledProductIU) {
		
		if(!checkProductStatus(dtoSaledProductIU.getCustomerId())) {
			throw new BaseException(new ErrorMessage(MessageType.CAR_STATUS_IS_ALREADY_SALED, dtoSaledProductIU.getCustomerId().toString()));
		}
		
		if(!checkAmount(dtoSaledProductIU)) {
			throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH, ""));
		}
		
		SaledProduct savedSaledCar = saledProductRepository.save(createSaledProduct(dtoSaledProductIU));
		
		List<Sepet> sepet =sepetRepository.findByCustomerId(dtoSaledProductIU.getCustomerId());
		List<Product> allProducts = new ArrayList<>();
		for (Sepet s : sepet) {
			s.getProduct().setProductStatusType(ProductStatusType.SALED);
		    allProducts.add(s.getProduct()); 
		    productRepository.save(s.getProduct());
		}

		
		Customer customer = savedSaledCar.getCustomer();
		customer.getAccount().setAmount(remaningCustomerAmount(customer));
		customerRepository.save(customer);
		sepetRepository.deleteByCustomer_Id(customer.getId());
		
		
		
		return toDTO(savedSaledCar, dtoSaledProductIU);
	}
	
	
	public DtoSaledProduct toDTO(SaledProduct saledProduct,DtoSaledProductIU dtoSaledProductIU) {
		DtoSaledProduct dtoSaledProduct = new DtoSaledProduct();
		DtoCustomer dtoCustomer = new DtoCustomer();
		DtoSeller dtoSeller = new DtoSeller();
		List<DtoProduct> dtoProduct = new ArrayList();
		
		List<Sepet> sepet =sepetRepository.findByCustomerId(dtoSaledProductIU.getCustomerId());
		List<Product> allProducts = new ArrayList<>();
		for (Sepet s : sepet) {
		    allProducts.add(s.getProduct()); 
		 
		}
		
		BeanUtils.copyProperties(saledProduct, dtoSaledProduct);
		BeanUtils.copyProperties(saledProduct.getCustomer(), dtoCustomer);
		BeanUtils.copyProperties(saledProduct.getSeller(), dtoSeller);
		BeanUtils.copyProperties(allProducts, dtoProduct);
		
		dtoSaledProduct.setCustomer(dtoCustomer);
		dtoSaledProduct.setSeller(dtoSeller);
		dtoSaledProduct.setProduct(dtoProduct);
		return dtoSaledProduct;
	}

}
