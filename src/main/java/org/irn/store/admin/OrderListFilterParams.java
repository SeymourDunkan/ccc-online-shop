package org.irn.store.admin;

public class OrderListFilterParams {
    private String customerEmail;
    private Integer orderId;
    private String[] statusValues;
    private Integer page;
    
	public OrderListFilterParams(String customerEmail, Integer orderId, String[] statusValues, Integer page) {
		this.customerEmail = customerEmail;
		this.orderId = orderId;
		this.statusValues = statusValues;
		this.page = page;
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
}
