package com.iexceed.appzillon.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iexceed.appzillon.product.model.Product;

public interface ProductRepo extends JpaRepository<Product,Long>{

}
