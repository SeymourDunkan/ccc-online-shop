package org.irn.store.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.irn.store.admin.OrderListFilterParams;
import org.irn.store.admin.UserListParams;
import org.irn.store.order.OrderDetails;
import org.irn.store.product.Product;
import org.irn.store.user.AuthCredentials;
import org.irn.store.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RequestParamsRetrievalHelperTest {
	
	private HttpServletRequest request;

	@BeforeEach
	void mockHttpRequest() {
		request = mock(HttpServletRequest.class);
		when(request.getParameter("email")).thenReturn("email@example.com");
		when(request.getParameter("password")).thenReturn("secret");
		when(request.getParameter("first_name")).thenReturn("Nick");
		when(request.getParameter("last_name")).thenReturn("Norman");
		when(request.getParameter("role")).thenReturn("admin");
		when(request.getParameter("type")).thenReturn("Boots");
		when(request.getParameter("brand")).thenReturn("Columbia");
		when(request.getParameter("model")).thenReturn("RR-34-B");
		when(request.getParameter("material")).thenReturn("rubber");
		when(request.getParameter("color")).thenReturn("red");
		when(request.getParameter("image")).thenReturn("image");
		when(request.getParameter("price")).thenReturn("100.23");
		when(request.getParameter("category")).thenReturn("12");
		when(request.getParameter("product_id")).thenReturn("44");
		when(request.getParameter("category_id")).thenReturn("12");
		when(request.getParameter("shipping_address")).thenReturn("5th Avenue");
		when(request.getParameter("recipient_name")).thenReturn("Michael Jackson");
		when(request.getParameter("recipient_phone")).thenReturn("1 000 500");
		when(request.getParameter("payment_method")).thenReturn("PayPal");
		when(request.getParameter("payment_method")).thenReturn("PayPal");
		when(request.getParameter("blocked")).thenReturn("YES");
		when(request.getParameter("user_id")).thenReturn("45");
		when(request.getParameter("page")).thenReturn("2");
		when(request.getParameter("status")).thenReturn("Registered");
		when(request.getParameter("customer_email")).thenReturn("email@example.com");
		when(request.getParameter("order_id")).thenReturn("23");
		when(request.getParameter("new_status")).thenReturn("Paid");
	}
	@Test
	void testRetrieveRegisterUserFormParams() {
		User user  = RequestParamsRetrievalHelper.retrieveRegisterUserFormParams(request);
		assertEquals("email@example.com", user.getEmail());
		assertEquals("secret", user.getPassword());
		assertEquals("Nick", user.getFirstName());
		assertEquals("Norman", user.getLastName());
		assertEquals("admin", user.getRole());
	}


	@Test
	void testRetrieveLoginFormParams() {
		AuthCredentials ac = new AuthCredentials("email@example.com", "secret");
		AuthCredentials ac2 = RequestParamsRetrievalHelper.retrieveLoginFormParams(request);
		verify(request, times(2)).getParameter(anyString());
		assertEquals(ac2.getPassword(), ac.getPassword());
		assertEquals(ac2.getEmail(), ac.getEmail());
	}

	@Test
	void testRetrieveAddProductFormParams() {
		Product product = RequestParamsRetrievalHelper.retrieveAddProductFormParams(request);
		assertEquals("Columbia", product.getBrand());
		assertEquals("red", product.getColor());
		assertEquals("RR-34-B", product.getModel());
		assertTrue(new BigDecimal("100.23").equals(product.getPrice()));
	}

	
	@Test
	void testRetrieveCheckoutFormParams() {
		OrderDetails orderDetails = RequestParamsRetrievalHelper.retrieveCheckoutFormParams(request);
		assertEquals("5th Avenue", orderDetails.getShippingAddress());
		assertEquals("Michael Jackson", orderDetails.getRecipientName());
		assertEquals("1 000 500", orderDetails.getRecipientPhone());
		assertEquals("PayPal", orderDetails.getPaymentMethod());
		
	}


	@Test
	void testRetrieveOrderListFilterParams() {
		OrderListFilterParams olfp = RequestParamsRetrievalHelper.retrieveOrderListFilterParams(request);
		assertEquals("email@example.com", olfp.getCustomerEmail());
		assertEquals("Paid", olfp.getNewStatus());
		assertEquals(23, olfp.getOrderId());
	}

	@Test
	void testGetProductId() {
		assertEquals(44, RequestParamsRetrievalHelper.getProductId(request));
	}

	@Test
	void testGetCategoryId() {
		assertEquals(12, RequestParamsRetrievalHelper.getCategoryId(request));
	}

	@Test
	void testRetrieveUserListParams() {
		UserListParams ulp = RequestParamsRetrievalHelper.retrieveUserListParams(request);
		assertEquals("YES", ulp.getBlocked());
		assertEquals(45, ulp.getUserId());
		assertEquals(2, ulp.getPage());
	}

}
