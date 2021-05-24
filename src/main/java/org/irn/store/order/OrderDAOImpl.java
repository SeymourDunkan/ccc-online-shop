package org.irn.store.order;

import javax.sql.DataSource;

public class OrderDAOImpl implements OrderDAO {

	private DataSource dataSource;

	public OrderDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	
}
