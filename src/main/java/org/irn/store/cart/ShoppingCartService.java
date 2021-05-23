package org.irn.store.cart;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.el.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.irn.store.product.Product;
import org.irn.store.product.ProductDAO;
import org.irn.store.product.ProductDAOImpl;
import org.irn.store.product.ProductService;

public class ShoppingCartService {
	private static final Logger LOGGER = LogManager.getLogger(ShoppingCartService.class);
    private ProductDAO productDAO;
    
	public ShoppingCartService(DataSource dataSource) {
		this.productDAO = new ProductDAOImpl(dataSource);
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// get request param 'action'

		String action = request.getParameter("action");
		if (action != null ) {
			List<String> allowedActions = Arrays.asList("add", "update", "delete");
			// allowed actions are ['add', 'update', 'delete']
			if ( allowedActions.contains(action)) {
				switch (action) {
				case "add":
					LOGGER.info("Action was " + action);
					addProduct(request, response);
					break;
				case "update":
					updateProductQuantity(request, response);
					break;
				case "delete":
					deleteProduct(request, response);
					break;
				default:
					defaulAction(request, response);
					break;
				}
			}
		} else {
			request.getRequestDispatcher("WEB-INF/front/shopping_cart.jsp").forward(request, response);
		}
		

		
	}

	private void defaulAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/front/shopping_cart.jsp").forward(request, response);
		
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void updateProductQuantity(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Integer product_id = Integer.parseInt(request.getParameter("product_id"));
		Product product = productDAO.getById(product_id);
		ShoppingCart cart = null;
		HashMap<Product, Integer> items = (HashMap<Product, Integer>) request.getSession().getAttribute("cart");
		
		if ( items == null ) {
			LOGGER.info("Creating shopping cart with empty map");
			cart = new ShoppingCart(new HashMap<Product, Integer>());
		} else {
			LOGGER.info("Creating shopping cart with existing map");
			cart = new ShoppingCart(items);
		}
		
		
		LOGGER.info("Adding product to the shopping cart " + product);
		cart.addItem(product);
		LOGGER.info("There are " + cart.getItems().get(product) + " of this product in the shopping cart and so far total amount is " + cart.getTotalAmount());
		request.getSession().setAttribute("cart", cart.getItems());
		request.getSession().setAttribute("total", cart.getTotalAmount());
		//request.getRequestDispatcher("WEB-INF/front/shopping_cart.jsp").forward(request, response);
		response.sendRedirect("view-cart");
	}
	
	
}
