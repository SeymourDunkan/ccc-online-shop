package org.irn.store.util;

public class PaginationHelper {
	private Integer recordsPerPage;
		
    public String getSQLByPageNumber(Integer pageNumber) {
    	Integer offset = (pageNumber -1) * recordsPerPage;
    	return " limit " + recordsPerPage + " offset " + offset;
    }

    public PaginationHelper(Integer recordsPerPage ) {
    	this.recordsPerPage = recordsPerPage;
    }
    
	public Integer getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(Integer recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
	
	public Integer getTotalNumberOfPages(Integer records) {
		Integer totalPages = records / recordsPerPage;
		if ( records % recordsPerPage > 0) {
			return totalPages + 1;
		}
		return totalPages;
	}

}
