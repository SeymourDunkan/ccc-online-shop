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

@WebServlet("/register-user")
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(RegisterUserServlet.class);

	@Resource(name = "jdbc/ccc_db")
	private DataSource dataSource;
	
    public RegisterUserServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		  LOGGER.info("RegisterUserServelt doGet forwarding to the registration form");
          request.getRequestDispatcher("WEB-INF/front/register_user_form.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = new UserService(dataSource);
		LOGGER.info("trying to forward to index page");
        userService.registerUser(request, response);
	}

}
