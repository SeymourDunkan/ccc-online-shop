package org.irn.store.admin;

import org.irn.store.order.OrderDetails;
import org.irn.store.user.User;

public class OrderUserDTO {
	private OrderDetails orderDetails;
	private User user;

	public OrderUserDTO(OrderDetails orderDetails, User user) {

		this.orderDetails = orderDetails;
		this.user = user;
	}

	public OrderDetails getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(OrderDetails orderDetails) {
		this.orderDetails = orderDetails;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public OrderUserDTO() {}

}
