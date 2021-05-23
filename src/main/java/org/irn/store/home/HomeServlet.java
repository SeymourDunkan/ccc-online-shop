package org.irn.store.home;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.irn.store.user.UserDAO;
import org.irn.store.user.UserDAOImpl;


@WebServlet({"", "/home"})
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/ccc_db")
	private DataSource dataSource;
	

    public HomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAOImpl(dataSource);
        userDAO.getAll();
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}


}
