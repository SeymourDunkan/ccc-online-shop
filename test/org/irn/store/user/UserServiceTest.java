package org.irn.store.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.irn.store.util.RequestParamsRetrievalHelper;
import org.irn.store.util.ValidationHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class UserServiceTest {

	RequestDispatcher requestDispatcher;

	private AutoCloseable closeable;

	@BeforeEach
	void testInit() {
		closeable = MockitoAnnotations.openMocks(this);
		requestDispatcher = new RequestDispatcher() {

			@Override
			public void include(ServletRequest request, ServletResponse response) throws ServletException, IOException {

			}

			@Override
			public void forward(ServletRequest request, ServletResponse response) throws ServletException, IOException {

			}
		};

	}

	@AfterEach
	public void releaseMocks() throws Exception {
		closeable.close();
	}

	HttpServletRequest request = mock(HttpServletRequest.class);

	HttpServletResponse response = mock(HttpServletResponse.class);

	@Mock
	UserDAOImpl userDAO;
	@Mock 
    DataSource dataSource;

	@Test
	void testRegisterUser() throws IOException, ServletException {

		when(request.getParameter("email")).thenReturn("email@example.com");
		when(request.getParameter("password")).thenReturn("secret34");
		when(request.getParameter("first_name")).thenReturn("Nick");
		when(request.getParameter("last_name")).thenReturn("Norman");
		when(request.getParameter("role")).thenReturn("admin");
		when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
		when(userDAO.getByEmail(anyString())).thenReturn(null);
		when(userDAO.create(any(User.class))).thenReturn(new User());
        UserService userService = new UserService(dataSource);
        userService.setUserDAO(userDAO);
		userService.registerUser(request, response);
		verify(userDAO, times(1)).getByEmail(anyString());
 		verify(response, times(1)).sendRedirect(anyString());


	}

	@Test
	void testLoginUser() {
		fail("Not yet implemented");
	}

	@Test
	void testListUsers() {
		fail("Not yet implemented");
	}

}
