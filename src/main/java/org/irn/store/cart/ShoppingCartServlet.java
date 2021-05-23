package org.irn.store.cart;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebServlet("/view-cart")
public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(ShoppingCartServlet.class);
	@Resource(name = "jdbc/ccc_db")
    private DataSource dataSource;
	
    public ShoppingCartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Request to 'view-cart'");
		String query = request.getQueryString();
		if ( query == null ) {
			request.getRequestDispatcher("WEB-INF/front/shopping_cart.jsp").forward(request, response);
			return;
		}
		ShoppingCartService shoppingCartService = new ShoppingCartService(dataSource);
		shoppingCartService.processRequest(request, response);
		//request.getRequestDispatcher("WEB-INF/front/shopping_cart.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
