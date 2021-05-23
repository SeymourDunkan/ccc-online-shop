package org.irn.store.product;

import java.util.List;

public interface ProductDAO {
	Product create(Product product);
	Product update(Product product);
	List<Product> getByCategory(Integer category_id);
}
