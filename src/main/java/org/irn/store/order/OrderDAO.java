package org.irn.store.order;

import java.util.List;

import org.irn.store.cart.ShoppingCart;

public interface OrderDAO {

	OrderDetails createOrder(ShoppingCart cart, OrderDetails orderDetails);
	OrderDetails getOrderById(Integer orderId);
	List<OrderDetails> getOrdersByUserId(Integer userId);
}
