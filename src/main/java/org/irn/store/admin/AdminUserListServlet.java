package org.irn.store.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.irn.store.user.UserDAOImpl;

@WebServlet("/admin/user-list")
public class AdminUserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public AdminUserListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// check if request param is present user_id=id&action=block
		// do the action 
		// toggleStatus method
		
		
		// get list of users
		// set attribute
		// render
		request.getRequestDispatcher("/WEB-INF/admin/admin_user_list.jsp").forward(request, response);
	}
}
