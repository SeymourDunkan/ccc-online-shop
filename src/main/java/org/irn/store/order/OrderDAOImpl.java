package org.irn.store.order;

import javax.sql.DataSource;

import org.irn.store.cart.ShoppingCart;

public class OrderDAOImpl implements OrderDAO {
    private final String INSERT_ORDER_DETAILS_SQL = "";
    private final String INSERT_PRODUCTS_INTO_ORDER_BY_ORDER_ID_SQL = "";
	private DataSource dataSource;

	public OrderDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public OrderDetails createOrder(ShoppingCart cart, OrderDetails orderDetails) {
		// addOrderDetails
		// addProductsIntoOrder
		return null;
	}
	
	private Integer addOrderDetails(OrderDetails orderDetails) {
		// TODO
		return null;
	}
	
	private void addProductsIntoOrder(ShoppingCart cart) {
		// TODO 
	}
	
	
	
}
