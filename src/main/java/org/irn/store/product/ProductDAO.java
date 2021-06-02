package org.irn.store.product;

import java.util.List;

public interface ProductDAO {
	Product create(Product product);
	Product update(Product product);
	void delete(Integer productId);
	List<Product> getByCategory(Integer categoryId);
	List<Product> getByCategoryAndPageNumber(Integer categoryId, String sqlToAdd);
	Product getById(Integer productId);
	Integer getTotalRecords(Integer categoryId);
}
