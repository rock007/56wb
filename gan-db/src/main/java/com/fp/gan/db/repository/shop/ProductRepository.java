package com.fp.gan.db.repository.shop;

import com.fp.gan.db.entity.shop.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
	
}