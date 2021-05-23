package org.irn.store.cart;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.el.stream.Stream;

public class ShoppingCartService {

	public void processRequest(HttpServletRequest request, HttpServletResponse response) {
		// get request param 'action'

		String action = request.getParameter("action");
		if (action != null ) {
			List<String> allowedActions = Arrays.asList("add", "update", "delete");
			// allowed actions are ['add', 'update', 'delete']
			if ( allowedActions.contains(action)) {
				switch (action) {
				case "add":
					addProduct(request, response);
					break;
				case "update":
					updateProductQuantity(request, response);
					break;
				case "delete":
					deleteProduct(request, response);
					break;
				default:
					defaulAction(request, response);
					break;
				}
			}
		}
		

		
	}

	private void defaulAction(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void updateProductQuantity(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void addProduct(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	
	
}
