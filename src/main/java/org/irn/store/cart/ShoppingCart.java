package org.irn.store.cart;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.irn.store.product.Product;

public class ShoppingCart {
    private Map<Product, Integer> cart = new HashMap<Product, Integer>();
    
    public void addItem(Product product) {
    	// can do lambda with map calcualte
    	if ( cart.containsKey(product)) {
    		Integer quantity = cart.get(product) + 1;
    		cart.put(product, quantity);
    	} else {
    		cart.put(product, 1);
    	}
    }
    
    public Map<Product, Integer> getItems() {
    	return this.cart;
    }
    
    public void removeItem(Product product) {
    	cart.remove(product);
    }
    
    public BigDecimal getTotalAmount() {
    	// can do lambda
    	BigDecimal total = new BigDecimal("0.0");
    	Iterator<Product> iterator = cart.keySet().iterator();
    	
    	while (iterator.hasNext()) {
			Product product = iterator.next();
			Integer quantity = cart.get(product);
			BigDecimal subTotal = product.getPrice().multiply(new BigDecimal(quantity));
			total.add(subTotal);
		}
    	return total;
    }
    
    public void clear() {
    	cart.clear();
    }
    
    public void updateItemQuantity(Product product, Integer quantity) {
    		cart.put(product, quantity);
    }
    
}
