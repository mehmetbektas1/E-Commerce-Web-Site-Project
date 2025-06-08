package com.eticaret.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eticaret.dto.DtoAddress;
import com.eticaret.dto.DtoProduct;
import com.eticaret.dto.DtoSeller;
import com.eticaret.dto.DtoSellerProduct;
import com.eticaret.dto.DtoSellerProductIU;
import com.eticaret.exception.BaseException;
import com.eticaret.exception.ErrorMessage;
import com.eticaret.exception.MessageType;
import com.eticaret.model.Product;
import com.eticaret.model.Seller;
import com.eticaret.model.ProductSeller;
import com.eticaret.repository.ProductRepository;
import com.eticaret.repository.SellerProductRepository;
import com.eticaret.repository.SellerRepository;
import com.eticaret.service.ISellerProductService;

@Service
public class SellerProductServiceImpl implements ISellerProductService{
	
	@Autowired
	private SellerProductRepository sellerProductRepository;
	
	@Autowired
	private SellerRepository sellerRepository;

	@Autowired
	private ProductRepository productRepository;
	
	private ProductSeller createSellerProduct(DtoSellerProductIU dtoSellerProductIU) {
		
		Optional<Seller> optSeller = sellerRepository.findById(dtoSellerProductIU.getSellerId());
		if(optSeller.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSellerProductIU.getSellerId().toString()));
		}

		Optional<Product> optProduct = productRepository.findById(dtoSellerProductIU.getProductId());
		if(optProduct.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSellerProductIU.getProductId().toString()));
		}
		
		
		ProductSeller productSeller = new ProductSeller();
		productSeller.setCreateTime(new Date());
		productSeller.setSeller(optSeller.get());
		productSeller.setProduct(optProduct.get());
		
		return productSeller;
		
	}
	
	@Override
	public DtoSellerProduct saveSellerProduct(DtoSellerProductIU dtoSellerProductIU) {
		DtoSellerProduct dtoSellerProduct = new DtoSellerProduct();
		DtoSeller dtoSeller = new DtoSeller();
		DtoProduct dtoProduct = new DtoProduct();
		
		DtoAddress dtoAddress = new DtoAddress();
		
		ProductSeller savedSellerProduct = sellerProductRepository.save(createSellerProduct(dtoSellerProductIU));
		
		BeanUtils.copyProperties(savedSellerProduct, dtoSellerProduct);
		BeanUtils.copyProperties(savedSellerProduct.getSeller(), dtoSeller);
		BeanUtils.copyProperties(savedSellerProduct.getSeller().getAddress(), dtoAddress);
		
		BeanUtils.copyProperties(savedSellerProduct.getProduct(), dtoProduct);
		
		
		dtoSeller.setAddress(dtoAddress);
		dtoSellerProduct.setSeller(dtoSeller);
		dtoSellerProduct.setProduct(dtoProduct);
		
		return dtoSellerProduct;
	}
	
	@Override
	public Long getByProductId(Long ProductId) {
		Optional<ProductSeller> optsellerproduct=sellerProductRepository.findByProduct_Id(ProductId);
		ProductSeller productSeller = new ProductSeller();
		productSeller=optsellerproduct.get();
		Seller seller=productSeller.getSeller();
		
		return seller.getId();
	}
	
	
	
	

}
