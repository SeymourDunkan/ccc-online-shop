package org.irn.store.order;

import java.util.List;

import org.irn.store.admin.OrderListFilterParams;
import org.irn.store.admin.OrderUserDTO;
import org.irn.store.cart.ShoppingCart;


public interface OrderDAO {

	OrderDetails createOrder(ShoppingCart cart, OrderDetails orderDetails);
	OrderDetails getOrderById(Integer orderId);
	List<OrderDetails> getOrdersByUserId(Integer userId);
	List<OrderUserDTO> getOrdersAndUsers(String paginationSql, OrderListFilterParams params);
	Integer getTotalRecords();
	void updateOrderStatus(Integer orderId, String newStatus);
}
