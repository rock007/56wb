package sam.wb.db.repository.shop;

import org.springframework.data.repository.CrudRepository;

import sam.wb.db.entity.shop.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	
}