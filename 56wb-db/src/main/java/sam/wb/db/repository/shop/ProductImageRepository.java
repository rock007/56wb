package sam.wb.db.repository.shop;

import org.springframework.data.repository.CrudRepository;

import sam.wb.db.entity.shop.ProductImage;

public interface ProductImageRepository extends CrudRepository<ProductImage, Long> {
	
}