package com.iexceed.appzillon.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.iexceed.appzillon.product.config.ProductConfig;
import com.iexceed.appzillon.product.controller.dto.Coupon;
import com.iexceed.appzillon.product.model.Product;
import com.iexceed.appzillon.product.repo.ProductRepo;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {

	@Autowired
	private ProductRepo repo;

	@Autowired
	private ProductConfig productConfig;

	@Value("${couponService.url}")
	private String couponServiceURL;

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public Product create(@RequestBody Product product) {
		Coupon coupon = productConfig.restTemplate().getForObject(couponServiceURL + product.getCouponCode(), Coupon.class);
		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		return repo.save(product);

	}

}

