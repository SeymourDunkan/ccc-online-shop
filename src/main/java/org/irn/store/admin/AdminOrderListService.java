package org.irn.store.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.irn.store.order.OrderDAO;
import org.irn.store.order.OrderDAOImpl;
import org.irn.store.order.OrderDetails;
import org.irn.store.user.User;
import org.irn.store.util.PaginationHelper;
import org.irn.store.util.RequestParamsRetrievalHelper;

public class AdminOrderListService {
	private static final Logger LOGGER = LogManager.getLogger(AdminOrderListService.class);
	private DataSource dataSource;
	
	public AdminOrderListService(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void renderOrderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderDAO orderDAO = new OrderDAOImpl(dataSource);
		OrderListFilterParams orderListFilterParams = RequestParamsRetrievalHelper.retrieveOrderListFilterParams(request);
		// 5 orders per page
		PaginationHelper paginationHelper = new PaginationHelper(5);
		
		String sqlToAppend = paginationHelper.getSQLByPageNumber(orderListFilterParams.getPage());
		LOGGER.info("SQL to append " + sqlToAppend);

		List<OrderUserDTO> ordersAndUsers = orderDAO.getOrdersAndUsers(sqlToAppend, orderListFilterParams);
		Integer totalRecords = orderDAO.getTotalRecords();
		LOGGER.info("Total records " + totalRecords);
		Integer numberOfPages = paginationHelper.getTotalNumberOfPages(totalRecords);
		request.setAttribute("ordersAndUsers", ordersAndUsers);
	    request.setAttribute("numberOfPages", numberOfPages);
		LOGGER.info("redirecting to orderlist " + "/WEB-INF/admin/admin_order_list.jsp?page=" + orderListFilterParams.getPage());
		request.getRequestDispatcher("/WEB-INF/admin/admin_order_list.jsp?page=" + orderListFilterParams.getPage()).forward(request, response);
	}
	
}
