package org.irn.store.order;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.irn.store.cart.ShoppingCart;
import org.irn.store.product.Product;
import org.irn.store.user.User;
import org.irn.store.util.RequestParamsRetrievalHelper;
import org.irn.store.util.ValidationHelper;

public class OrderService {
	private OrderDAO orderDAO;
	private final static Logger LOGGER = LogManager.getLogger(OrderService.class);
	
	
    public OrderService(DataSource dataSource) {
		this.orderDAO = new OrderDAOImpl(dataSource);
	}


	public void processOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	OrderDetails orderDetails = RequestParamsRetrievalHelper.retrieveCheckoutFormParams(request);
    	ValidationHelper validationHelper = new ValidationHelper<OrderDetails>();
    	List<String> orderDetailsValidationErrorMessages = validationHelper.validate(orderDetails);
    	
    	if ( !orderDetailsValidationErrorMessages.isEmpty() ) {
    		LOGGER.error("There were some errors in the checkout form. Forwarding to the checkout again");
    		request.setAttribute("errorMessages", orderDetailsValidationErrorMessages);
    		request.getRequestDispatcher("WEB-INF/front/checkout_form.jsp").forward(request, response);
    		return;
    	}
    	
    	Integer userId = ((User) request.getSession().getAttribute("user")).getId();
    	LOGGER.info("User id is " + userId);
    	
    	orderDetails.setUserId(userId);
    	
    	HashMap<Product, Integer> itemsInShoppingCart = (HashMap<Product, Integer>) request.getSession().getAttribute("cart");
    	ShoppingCart cart = new ShoppingCart(itemsInShoppingCart);

    	orderDAO.createOrder( cart, orderDetails);
    
    	request.getSession().setAttribute("cart", new HashMap<Product, Integer>());
    	// epmpty cart
    	// redirect to orders
    	
    	LOGGER.info("New product order was saved. Redirecting to user-orders");
    	response.sendRedirect("user-orders");
    	
    }
}
