package org.irn.store.cart;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.irn.store.product.Product;
import org.junit.jupiter.api.Test;

class ShoppingCartTest {

	
	@Test
	void testAddItem() {
		Map<Product, Integer> items = new HashMap<Product, Integer>();
		ShoppingCart cart = new ShoppingCart(items);
		Product product = new Product();
		assertTrue(cart.getItems().isEmpty());
		assertFalse(cart.getItems().containsKey(product));
		cart.addItem(product);
		assertTrue(cart.getItems().containsKey(product));
		assertEquals(1, cart.getItems().get(product));
		cart.addItem(product);
		assertEquals(2, cart.getItems().get(product));
	}
	@Test
	void testGetItems() {
		
	}

	@Test
	void testRemoveItem() {
		
	}

	@Test
	void testSetItems() {
		
	}

	@Test
	void testGetTotalAmount() {
		
	}

	@Test
	void testClear() {
		
	}

	@Test
	void testUpdateItemQuantity() {
		
	}

}
