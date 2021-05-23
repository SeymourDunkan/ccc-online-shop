package org.irn.store.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.irn.store.user.User;
import org.irn.store.user.UserDAO;
import org.irn.store.user.UserDAOImpl;
import org.irn.store.user.UserService;
import org.irn.store.util.RequestParamsRetrievalHelper;
import org.irn.store.util.ValidationHelper;

public class ProductService {
	private static final Logger LOGGER = LogManager.getLogger(ProductService.class);

	private ProductDAO productDAO;
	
    public ProductService(DataSource dataSource) {

		productDAO = new ProductDAOImpl(dataSource);
	}

	public void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ValidationHelper<Product> validationHelper = new ValidationHelper<Product>();

        // get User with form helper
        Product product = RequestParamsRetrievalHelper.retrieveAddProductFormParams(request);
       
        // validate User with validation helper
        List<String> productValidationErrorMessages = validationHelper.validate(product);
        
        // if there are no validation errors add product to the db
        if ( productValidationErrorMessages.isEmpty() ) {
        	LOGGER.info("Add Product Form Validation is ok. Adding prodcut to db");
        	LOGGER.info("Product " + product);
            productDAO.create(product);
            response.sendRedirect(request.getContextPath() + "/admin/product-list");
        } else {
        	LOGGER.info("Form Validation is not ok. Forwarding to add product page again");
        	LOGGER.info(productValidationErrorMessages);
        	request.setAttribute("errorMessages", productValidationErrorMessages);
        	request.getRequestDispatcher("WEB-INF/admin/admin_product_form.jsp").forward(request, response);
        	return;
        }
	
	}

	public void renderProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestCategoryId = request.getParameter("category_id");
		Integer categoryId = null;
		if ( requestCategoryId != null ) {
			categoryId = Integer.parseInt(requestCategoryId);
		}
		List<Product> products = productDAO.getByCategory(categoryId);
		
		request.setAttribute("products", products);
		LOGGER.info(products);
		request.getRequestDispatcher("WEB-INF/front/product_list.jsp?category_id="+categoryId).forward(request, response);
	}
    
    
}