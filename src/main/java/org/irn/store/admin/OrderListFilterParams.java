package org.irn.store.admin;

import java.util.Arrays;

public class OrderListFilterParams {
    private String customerEmail;
    private Integer orderId;
    private String[] statusValues;
    private Integer page;
    private String newStatus;
    
	public String getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus;
	}

	public OrderListFilterParams(String customerEmail, Integer orderId, String[] statusValues, Integer page, String newStatus) {
		this.customerEmail = customerEmail;
		this.orderId = orderId;
		this.statusValues = statusValues;
		this.page = page;
		this.newStatus = newStatus;
	}

	public OrderListFilterParams() {
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String[] getStatusValues() {
		return statusValues;
	}

	public void setStatusValues(String[] statusValues) {
		this.statusValues = statusValues;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	
	public boolean isOnlyPagePresent() {
		return ( orderId == null && statusValues == null && customerEmail == null );
	}

	@Override
	public String toString() {
		return "OrderListFilterParams [customerEmail=" + customerEmail + ", orderId=" + orderId + ", statusValues="
				+ Arrays.toString(statusValues) + ", page=" + page + ", newStatus=" + newStatus + "]";
	}
	
	
}
