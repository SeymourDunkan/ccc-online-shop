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
import org.irn.store.product.ProductService;

@WebServlet("/admin/delete-product")
public class AdminDeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(AdminDeleteProductServlet.class);

	@Resource(name = "jdbc/ccc_db")
	private DataSource dataSource;

    public AdminDeleteProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService productService = new ProductService(dataSource);
		productService.deleteProduct(request, response);
	}

}
