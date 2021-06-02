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

@WebServlet("/admin/edit-product")
public class AdminEditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(AdminEditProductServlet.class);

	@Resource(name = "jdbc/ccc_db")
	private DataSource dataSource;

    public AdminEditProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// render first time or on error
		ProductService productService = new ProductService(dataSource);
		productService.renderProduct(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// update if ok and redirect to product list category equal to category
		ProductService productService = new ProductService(dataSource);
		productService.updateProduct(request, response);
	}

}
