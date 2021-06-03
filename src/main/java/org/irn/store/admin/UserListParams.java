package org.irn.store.admin;

public class UserListParams {
     private Integer page;
     private Integer userId;
     private String blocked;
     
	 public UserListParams(Integer page, Integer userId, String blocked) {
    	this.page = page;
		this.userId = userId;
		this.blocked = blocked;
	 }

    public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getBlocked() {
		return blocked;
	}

	public void setBlocked(String blocked) {
		this.blocked = blocked;
	}

	public boolean requestedToggleBlocked() {
    	return userId != null && blocked != null;
    }
}
