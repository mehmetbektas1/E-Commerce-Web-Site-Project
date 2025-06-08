package com.eticaret.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eticaret.dto.DtoProduct;
import com.eticaret.dto.DtoProductIU;
import com.eticaret.model.Product;
import com.eticaret.repository.ProductRepository;
import com.eticaret.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService{
	
	@Autowired
	private ProductRepository productRepository;

	
	private Product createProduct(DtoProductIU dtoProductIU) {
		Product product = new Product();
		product.setCreateTime(new Date());
		
		BeanUtils.copyProperties(dtoProductIU, product);
		return product;
	}
	
	@Override
	public DtoProduct saveProduct(DtoProductIU dtoProductIU) {
		DtoProduct dtoProduct = new DtoProduct();
		Product savedProduct =  productRepository.save(createProduct(dtoProductIU));
		
		BeanUtils.copyProperties(savedProduct, dtoProduct);
		return dtoProduct;
	}

	@Override
	public List<DtoProduct> findProduct() {
	    List<Product> products = productRepository.findAll();
	    List<DtoProduct> dtoProducts = new ArrayList<>();

	    for (Product product : products) {
	        DtoProduct dto = new DtoProduct();
	        BeanUtils.copyProperties(product, dto);
	        dtoProducts.add(dto);
	    }

	    return dtoProducts;
	}

	
}
