package org.irn.store.order;

import org.irn.store.cart.ShoppingCart;

public interface OrderDAO {

	OrderDetails createOrder(ShoppingCart cart, OrderDetails orderDetails);
	OrderDetails getOrderById(Integer orderId);
}
