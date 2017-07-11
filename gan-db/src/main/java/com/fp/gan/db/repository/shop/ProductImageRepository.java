package com.fp.gan.db.repository.shop;

import com.fp.gan.db.entity.shop.ProductImage;
import org.springframework.data.repository.CrudRepository;

public interface ProductImageRepository extends CrudRepository<ProductImage, Long> {
	
}