package org.irn.store.user;

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
import org.irn.store.order.OrderService;

@WebServlet("/user-orders")
public class UserOrdersServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = LogManager.getLogger(UserOrdersServlet.class);
    @Resource(name = "jdbc/ccc_db")
    private DataSource dataSource;
    
    public UserOrdersServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderService orderService = new OrderService(dataSource);
		orderService.showUserOrders(request, response);
	}

}
