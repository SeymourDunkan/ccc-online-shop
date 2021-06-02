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
import org.irn.store.util.PaginationHelper;
import org.irn.store.util.RequestParamsRetrievalHelper;
import org.irn.store.util.ValidationHelper;

public class ProductService {
	private static final Logger LOGGER = LogManager.getLogger(ProductService.class);

	private String requestCategoryId;
	private String requestCurrentPage;
	private Integer currentPage;
	private Integer categoryId;
	private ProductDAO productDAO;
	
    public ProductService(DataSource dataSource) {
		productDAO = new ProductDAOImpl(dataSource);
	}

	public void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ValidationHelper<Product> validationHelper = new ValidationHelper<Product>();
        Product product = RequestParamsRetrievalHelper.retrieveAddProductFormParams(request);
        List<String> productValidationErrorMessages = validationHelper.validate(product);
        
        // if there are no validation errors add product to the db
        if ( productValidationErrorMessages.isEmpty() ) {
        	LOGGER.info("Add Product Form Validation is ok. Adding prodcut to db");
        	LOGGER.info("Product " + product);
            Product newProduct = productDAO.create(product);
            response.sendRedirect(request.getContextPath() + "/admin/product-list?category_id="+newProduct.getCategoryId());
        } else {
        	LOGGER.info("Form Validation is not ok. Forwarding to add product page again");
        	LOGGER.info(productValidationErrorMessages);
        	request.setAttribute("errorMessages", productValidationErrorMessages);
        	request.getRequestDispatcher("WEB-INF/admin/admin_product_form.jsp").forward(request, response);
        	return;
        }
	
	}
	
	// edit-product POST request by product_id
	public void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ValidationHelper<Product> validationHelper = new ValidationHelper<Product>();
        Product product = RequestParamsRetrievalHelper.retrieveAddProductFormParams(request);
        List<String> productValidationErrorMessages = validationHelper.validate(product);
        
        // if there are no validation errors update product in the db
        if ( productValidationErrorMessages.isEmpty() ) {
        	LOGGER.info("Edit Product Form Validation is ok");
        	LOGGER.info("Product from the form " + product);
        	LOGGER.info("Updating product");
        	LOGGER.info("Redirecting to product list");
            productDAO.update(product);
            response.sendRedirect(request.getContextPath() + "/admin/product-list?category_id=" + product.getCategoryId());
            return;
        } else {
        	LOGGER.info("Form Validation is not ok. Forwarding to add product page again");
        	LOGGER.info(productValidationErrorMessages);
        	request.setAttribute("errorMessages", productValidationErrorMessages);
        	request.getRequestDispatcher("WEB-INF/admin/admin_edit_product_form.jsp").forward(request, response);
        	return;
        }
	
	}
	
	// edit-product GET request by product_id
	public void renderProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          Integer productId = RequestParamsRetrievalHelper.getProductId(request);
          if ( productId != null ) {
        	  Product product = productDAO.getById(productId);
        	  LOGGER.info("Product by id " + product);
        	  if ( product.getId() == null  ) {
        		  response.sendError(404);
        		  return;
        	  }
        	  
        	  request.setAttribute("product", product);
        	  request.getRequestDispatcher("/WEB-INF/admin/admin_edit_product_form.jsp").forward(request, response);
        	  return;
          } else {
        	  response.sendError(404);
        	  return;
          }
	}

	public void renderProducts(HttpServletRequest request, HttpServletResponse response, Integer productsPerPage) throws ServletException, IOException {
		getCategoryIdAndPage(request, response);
		PaginationHelper paginationHelper = new PaginationHelper(productsPerPage);
		String sqlToAppend = paginationHelper.getSQLByPageNumber(currentPage);
		LOGGER.info("SQL to append " + sqlToAppend);
		List<Product> products  = productDAO.getByCategoryAndPageNumber(categoryId, sqlToAppend);
		Integer totalRecords = productDAO.getTotalRecords(categoryId);
		Integer numberOfPages = paginationHelper.getTotalNumberOfPages(totalRecords);
		request.setAttribute("products", products);
	    request.setAttribute("numberOfPages", numberOfPages);
	    request.setAttribute("categoryId", categoryId);
		request.getRequestDispatcher("WEB-INF/front/product_list.jsp?category_id="+categoryId+"&page=" + currentPage).forward(request, response);
	}
	
	public void renderProductsAdmin(HttpServletRequest request, HttpServletResponse response, Integer productsPerPage) throws ServletException, IOException {
		getCategoryIdAndPage(request, response);
		PaginationHelper paginationHelper = new PaginationHelper(productsPerPage);
		String sqlToAppend = paginationHelper.getSQLByPageNumber(currentPage);
		LOGGER.info("SQL to append " + sqlToAppend);
		List<Product> products  = productDAO.getByCategoryAndPageNumber(categoryId, sqlToAppend);
		Integer totalRecords = productDAO.getTotalRecords(categoryId);
		Integer numberOfPages = paginationHelper.getTotalNumberOfPages(totalRecords);
		request.setAttribute("products", products);
	    request.setAttribute("numberOfPages", numberOfPages);
	    request.setAttribute("categoryId", categoryId);
		request.getRequestDispatcher("/WEB-INF/admin/admin_product_list.jsp?category_id="+categoryId+"&page=" + currentPage).forward(request, response);
	}

	// this sets necessary class variables for pagination
	private void getCategoryIdAndPage(HttpServletRequest request, HttpServletResponse response) {
		requestCategoryId = request.getParameter("category_id");
		requestCurrentPage = request.getParameter("page");
		if ( requestCurrentPage == null ) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(requestCurrentPage);
		}
		if ( requestCategoryId != null ) {
			categoryId = Integer.parseInt(requestCategoryId);
		}
	}

	public void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Integer productId = RequestParamsRetrievalHelper.getProductId(request);
		Integer categoryId = RequestParamsRetrievalHelper.getCategoryId(request);
        if ( productId != null ) {
          productDAO.delete(productId);
          response.sendRedirect(request.getContextPath() + "/admin/product-list?category_id=" + categoryId); 
      	  return;
        } else {
      	  response.sendError(404);
      	  return;
        }
	}
}
