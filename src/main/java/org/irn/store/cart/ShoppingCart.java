package org.irn.store.cart;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.irn.store.product.Product;
import org.irn.store.product.ProductService;

public class ShoppingCart {
	private static final Logger LOGGER = LogManager.getLogger(ShoppingCart.class);
    private Map<Product, Integer> cart = new HashMap<Product, Integer>();

    public ShoppingCart(Map<Product, Integer> cart) {
		this.cart = cart;
	}

	public void addItem(Product product) {
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
    
    public void setItems(HashMap<Product, Integer> items) {
    	this.cart = items;
    }
    
    public BigDecimal getTotalAmount() {
    	BigDecimal total = new BigDecimal("0.0");
    	Iterator<Product> iterator = cart.keySet().iterator();
    	
    	while (iterator.hasNext()) {
    		LOGGER.info("Iterating over the map");
			Product product = iterator.next();
			Integer quantity = cart.get(product);
			BigDecimal subTotal = product.getPrice().multiply(new BigDecimal(quantity));
			LOGGER.info("Price for subtotal is " + subTotal);
			total = total.add(subTotal);
			LOGGER.info("Price for total is " + total);
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
