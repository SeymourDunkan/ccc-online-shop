package org.irn.store.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user-orders")
public class UserOrdersServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    public UserOrdersServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("WEB-INF/front/user_orders_list.jsp").forward(request, response);
	}

}
