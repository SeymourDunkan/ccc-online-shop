package org.irn.store.util;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.irn.store.admin.OrderListFilterParams;
import org.irn.store.order.OrderDetails;
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
	
	public static OrderDetails retrieveCheckoutFormParams(HttpServletRequest request) {
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setShippingAddress(request.getParameter("shipping_address"));
		orderDetails.setRecipientName(request.getParameter("recipient_name"));
		orderDetails.setRecipientPhone(request.getParameter("recipient_phone"));
		orderDetails.setPaymentMethod(request.getParameter("payment_method"));
		return orderDetails;
	}
	
	public static OrderListFilterParams retrieveOrderListFilterParams(HttpServletRequest request) {
		// this might be null
		String[] statusValues = request.getParameterValues("status");
		
		String customerEmail = request.getParameter("customer_email");
		String stringOrderId = request.getParameter("order_id");
		Integer orderId = null;
		if ( stringOrderId != null ) {
			orderId = Integer.parseInt(stringOrderId);
		}
		
		String stringPage = request.getParameter("page");
		Integer page = null;
		if ( stringPage != null ) {
			page = Integer.parseInt(stringPage);
		} else { 
			page = 1;
		}
		return new OrderListFilterParams(customerEmail, orderId, statusValues, page);
	}
}
