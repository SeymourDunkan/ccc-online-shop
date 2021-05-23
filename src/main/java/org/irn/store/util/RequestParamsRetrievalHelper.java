package org.irn.store.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.irn.store.product.Product;
import org.irn.store.user.AuthCredentials;
import org.irn.store.user.User;

public class RequestParamsRetrievalHelper {
     public static User retrieveRegisterUserFormParams(HttpServletRequest request) {
    	 return new User(
    			 request.getParameter("email").trim(),
    			 request.getParameter("password").trim(),
    			 request.getParameter("first_name").trim(),
    			 request.getParameter("last_name").trim(),
    			 request.getParameter("role").trim()); 
     }
     public static AuthCredentials retrieveLoginFormParams(HttpServletRequest request) {
    	 return new AuthCredentials(
    			 request.getParameter("email").trim(),
    			 request.getParameter("password").trim()); 
     }
	public static Product retrieveAddProductFormParams(HttpServletRequest request) {
		Product product = new Product();
		product.setType(request.getParameter("type"));
		product.setBrand(request.getParameter("brand").trim());
		product.setModel(request.getParameter("model").trim());
		product.setMaterial(request.getParameter("material").trim());
		product.setColor(request.getParameter("color").trim());
		product.setImage(request.getParameter("image").trim());
		product.setPrice(new BigDecimal(request.getParameter("price").trim()));
		product.setDateAdded(new Date());
		product.setCategoryId(Integer.parseInt(request.getParameter("category").trim()));
		return product;
	}
}
