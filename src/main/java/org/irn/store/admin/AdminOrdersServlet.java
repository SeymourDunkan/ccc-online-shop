package org.irn.store.admin;

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


@WebServlet("/admin/order-list")
public class AdminOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(AdminOrdersServlet.class);

	@Resource(name = "jdbc/ccc_db")
	private DataSource dataSource;
    public AdminOrdersServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminOrderListService orderListService = new AdminOrderListService(dataSource);
        String ordersPerPage = getServletContext().getInitParameter("ordersPerPage");
        LOGGER.info("There should be " + ordersPerPage + " orders per page");
        orderListService.renderOrderList(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
