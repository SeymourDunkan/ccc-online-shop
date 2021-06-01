package org.irn.store.order;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.irn.store.admin.OrderListFilterParams;
import org.irn.store.admin.OrderUserDTO;
import org.irn.store.cart.ShoppingCart;
import org.irn.store.product.Product;
import org.irn.store.user.User;

public class OrderDAOImpl implements OrderDAO {
	private static final Logger LOGGER = LogManager.getLogger(OrderDAOImpl.class);
    private final String INSERT_ORDER_DETAILS_SQL = "insert into order_details (user_id, shipping_address, recipient_name, recipient_phone, payment_method, total, status) VALUES (?,?,?,?,?,?,?)";
    private final String INSERT_PRODUCTS_INTO_ORDER_BY_ORDER_ID_SQL = "insert into product_order (order_id, product_id, quantity, subtotal) VALUES (?,?,?,?)";
    private final String GET_ORDER_BY_ORDER_ID_SQL = "select * from order_details where order_id=?";
    private final String GET_ORDERS_BY_USER_ID_SQL = "select * from order_details where user_id=?";
    private final String GET_ORDERS_AND_USERS_SQL = "select * from order_details inner join users on order_details.user_id=users.user_id order by order_details.order_id desc";
    private final String GET_TOTAL_RECORDS_NO_FILTER_SQL = "select count(*) from order_details inner join users on order_details.user_id=users.user_id";
    
	private DataSource dataSource;
	
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	

	public OrderDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public OrderDetails createOrder(ShoppingCart cart, OrderDetails orderDetails) {

			OrderDetails saveDetails  = addOrderDetails(orderDetails, cart);
	        addProductsIntoOrder(cart, saveDetails.getOrderId());

		return saveDetails;
	}
	
	private OrderDetails addOrderDetails(OrderDetails orderDetails, ShoppingCart cart) {
		
		LOGGER.info("OrderDAO adding OrderDetails");
		OrderDetails savedOrderDetails = null;
		Integer insertedOrderId = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(INSERT_ORDER_DETAILS_SQL, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, orderDetails.getUserId());
            stmt.setString(2, orderDetails.getShippingAddress());
            stmt.setString(3, orderDetails.getRecipientName());
            stmt.setString(4, orderDetails.getRecipientPhone());
            stmt.setString(5, orderDetails.getPaymentMethod());
            stmt.setBigDecimal(6, cart.getTotalAmount());
            stmt.setString(7, "Registered");
            
			int affectedRows = stmt.executeUpdate();
			
			if (affectedRows > 0) {
				LOGGER.info("Successfuly saved order into db");
				rs = stmt.getGeneratedKeys();
				LOGGER.info("Getting order_id");
				while (rs.next()) {
                    insertedOrderId = rs.getInt(1);
				}
				savedOrderDetails = getOrderById(insertedOrderId);
				LOGGER.info(savedOrderDetails);
			} else {
				LOGGER.info("Error. New order was not saved to database");
			}
		} catch (SQLException e) {
			LOGGER.error("Some SQL error happened while adding new order");
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		
		return savedOrderDetails;
	}
	
	private void addProductsIntoOrder(ShoppingCart cart, Integer orderId) {
 		 try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(INSERT_PRODUCTS_INTO_ORDER_BY_ORDER_ID_SQL);

			Map<Product, Integer> items = cart.getItems();
			Set<Product> products = items.keySet();
			
			for (Product product : products ) {
                stmt.setInt(1, orderId);
                stmt.setInt(2, product.getId());
				stmt.setInt(3, items.get(product));
				stmt.setBigDecimal(4, product.getPrice().multiply(new BigDecimal(items.get(product))));
				stmt.executeUpdate();
			}

		} catch (SQLException e) {
			LOGGER.error("Something whent wrong while adding products into the order");
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
	}
	
	private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
		try {
			if ( rs != null) {
				rs.close();
			}
			if ( stmt != null) {
				stmt.close();
			}
			if ( conn != null) {
				conn.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public OrderDetails getOrderById(Integer orderId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		OrderDetails orderDetails = new OrderDetails();
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(GET_ORDER_BY_ORDER_ID_SQL);
			stmt.setInt(1, orderId);
			rs = stmt.executeQuery();
			
			while ( rs.next() ) {
				orderDetails.setOrderId(rs.getInt(1));
				orderDetails.setUserId(rs.getInt(2));
				orderDetails.setOrderDate(rs.getTimestamp(3));
				orderDetails.setShippingAddress(rs.getString(4));
				orderDetails.setRecipientName(rs.getString(5));
				orderDetails.setRecipientPhone(rs.getString(6));
				orderDetails.setPaymentMethod(rs.getString(7));
				orderDetails.setTotal(rs.getBigDecimal(8));
				orderDetails.setStatus(rs.getString(9));
			}
			
			
		} catch (SQLException e) {
			LOGGER.error("Something went wrong while getting order details by id");
			e.printStackTrace();
		} finally {
			 closeResources(conn, stmt, rs);
		}
		
		LOGGER.error("This is what is returned from getOrderById method " + orderDetails);
		return orderDetails;
	}

	@Override
	public List<OrderDetails> getOrdersByUserId(Integer userId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<OrderDetails> orderDetailsList = new ArrayList<>();
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(GET_ORDERS_BY_USER_ID_SQL);
			stmt.setInt(1, userId);
			rs = stmt.executeQuery();
			
			while ( rs.next() ) {
				OrderDetails orderDetails = new OrderDetails();
				orderDetails.setOrderId(rs.getInt(1));
				orderDetails.setUserId(rs.getInt(2));
				orderDetails.setOrderDate(rs.getTimestamp(3));
				orderDetails.setShippingAddress(rs.getString(4));
				orderDetails.setRecipientName(rs.getString(5));
				orderDetails.setRecipientPhone(rs.getString(6));
				orderDetails.setPaymentMethod(rs.getString(7));
				orderDetails.setTotal(rs.getBigDecimal(8));
				orderDetails.setStatus(rs.getString(9));
				orderDetailsList.add(orderDetails);
			}
			
			
		} catch (SQLException e) {
			LOGGER.error("Something went wrong while getting order details by user id");
			e.printStackTrace();
		} finally {
			 closeResources(conn, stmt, rs);
		}
		
		LOGGER.error("This is what is returned from getOrderByUserId method " + orderDetailsList);
		return orderDetailsList;
	}
	
	public List<OrderUserDTO> getOrdersAndUsers(String paginationSql, OrderListFilterParams params) {
		List<OrderUserDTO> ordersAndUsers = new ArrayList<OrderUserDTO>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = GET_ORDERS_AND_USERS_SQL;
        if ( params.isOnlyPagePresent()) {
        	LOGGER.info("Only 'page' param is present. Getting orders and users.");
        	try {
				conn = dataSource.getConnection();
				sql = sql + paginationSql; // TODO
				LOGGER.info("Sql for users and orders " + sql);
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while ( rs.next() ) {
					
					User user = new User();
					user.setFirstName(rs.getString("first_name"));
					user.setLastName(rs.getString("last_name"));
					user.setId(rs.getInt(2));
					user.setEmail(rs.getString("email"));
					user.setRole(rs.getString("role"));
					user.setBlocked(rs.getString("blocked"));
					LOGGER.info(user);
					OrderDetails orderDetails = new OrderDetails();
					orderDetails.setOrderId(rs.getInt(1));
					orderDetails.setUserId(rs.getInt(2));
					orderDetails.setOrderDate(rs.getTimestamp(3));
					orderDetails.setShippingAddress(rs.getString(4));
					orderDetails.setRecipientName(rs.getString(5));
					orderDetails.setRecipientPhone(rs.getString(6));
					orderDetails.setPaymentMethod(rs.getString(7));
					orderDetails.setTotal(rs.getBigDecimal(8));
					orderDetails.setStatus(rs.getString(9));
					LOGGER.info(orderDetails);
					ordersAndUsers.add(new OrderUserDTO(orderDetails, user));
				}
			} catch (SQLException e) {
				LOGGER.error("Something whent wrong while retrieving orders and users");
			} finally {
				
			}
        }
		
		// no filter params, only page page=1
		
		// only order_id and page=1 (page not present so it defaults to 1)
		
		// page and email
		
		// page, email and status
		
		
		return ordersAndUsers;
	}

	public Integer getTotalRecords() {
		Connection conn = null;
		PreparedStatement stmt= null;
		ResultSet rs = null;
		Integer numberOfRecords = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(GET_TOTAL_RECORDS_NO_FILTER_SQL);
			rs = stmt.executeQuery();
			rs.next();
			numberOfRecords = rs.getInt(1);
		} catch (SQLException e) {
			LOGGER.error("Some SQL error happened while getting total number of records of orders and users join");
			e.printStackTrace();
		} finally {
			closeResourcesWithStatement(conn, stmt, rs);
		}

		return numberOfRecords;
	}
	
	private void closeResourcesWithPreparedStatement(Connection conn, PreparedStatement stmt, ResultSet rs) {
		try {
			if ( rs != null) {
				rs.close();
			}
			if ( stmt != null) {
				stmt.close();
			}
			if ( conn != null) {
				conn.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	private void closeResourcesWithStatement(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if ( rs != null) {
				rs.close();
			}
			if ( stmt != null) {
				stmt.close();
			}
			if ( conn != null) {
				conn.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
