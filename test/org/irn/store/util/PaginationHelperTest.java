package org.irn.store.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PaginationHelperTest {

	@Test
	void testGetSQLByPageNumber() {
		PaginationHelper ph = new PaginationHelper(10);
		String expected = " limit 10 offset 40";
		System.out.println(ph.getSQLByPageNumber(5));
		assertTrue(expected.equals(ph.getSQLByPageNumber(5)));
	
	}
	@Test
	void testGetRecordsPerPage() {
		PaginationHelper ph = new PaginationHelper(10);
		assertEquals(10, ph.getRecordsPerPage());
	}

	@Test
	void testSetRecordsPerPage() {
		PaginationHelper ph = new PaginationHelper(10);
		ph.setRecordsPerPage(20);
		Integer expected = 20;
		assertEquals(expected, ph.getRecordsPerPage());
	}

	@Test
	void testGetTotalNumberOfPages() {
		PaginationHelper ph = new PaginationHelper(10);
		Integer expected = 10;
		assertEquals(expected, ph.getTotalNumberOfPages(100));
	}
	
	@Test
	void testGetTotalNumberOfPagesHasDivRemainder() {
		PaginationHelper ph = new PaginationHelper(10);
		Integer expected = 11;
		assertEquals(expected, ph.getTotalNumberOfPages(101));
	}

}
