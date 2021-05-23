package org.irn.store.user;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.irn.store.util.RequestParamsRetrievalHelper;
import org.irn.store.util.ValidationHelper;
import org.mindrot.jbcrypt.BCrypt;

public class UserService {
	private static final Logger LOGGER = LogManager.getLogger(UserService.class);
	private DataSource dataSource;
	private UserDAO userDAO;
	
    public UserService(DataSource dataSource) {
		this.dataSource = dataSource;
		userDAO = new UserDAOImpl(dataSource);
	}
    
	public void registerUser(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	        ValidationHelper<User> validationHelper = new ValidationHelper<User>();

    	        // get User with form helper
    	        User user = RequestParamsRetrievalHelper.retrieveRegisterUserFormParams(request);
    	       
    	        // validate User with validation helper
    	        List<String> userValidationErrorMessages = validationHelper.validate(user);
    	        
    	        // if there are no validation errors then check if the user exists already by email
    	        // if the user exists then add corresponding message to the userValidationErrorMessages
                if ( userValidationErrorMessages.isEmpty() ) {
                	User existingUser = userDAO.getByEmail(user.getEmail());
                	if ( existingUser != null ) {
                		userValidationErrorMessages.add("A user with the email you have provided already exists.");
                		// forward early
                		request.setAttribute("errorMessages", userValidationErrorMessages);
                		request.setAttribute("errorUserInfo", user);
                    	request.getRequestDispatcher("WEB-INF/front/register_user_form.jsp").forward(request, response);
                    	return;
                	}
                } else {
                	// forward early
                	LOGGER.info("Form Validation is not ok. Forwarding to register page again");
                	LOGGER.info(userValidationErrorMessages);
                	request.setAttribute("errorMessages", userValidationErrorMessages);
                	request.setAttribute("errorUserInfo", user);
                	request.getRequestDispatcher("WEB-INF/front/register_user_form.jsp").forward(request, response);
                	return;
                }
                LOGGER.info("Form Validation is ok. Registering user " + user);
                // store record in db
                if (userDAO.create(user) == null )  {
                	LOGGER.info("User was not added");
                }
                response.sendRedirect("login");
    }

	public void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ValidationHelper<AuthCredentials> validationHelper = new ValidationHelper<AuthCredentials>();

        // get credentials with form helper
        AuthCredentials authCredentials = RequestParamsRetrievalHelper.retrieveLoginFormParams(request);
       
        // validate Login form with validation helper
        List<String> credentialsValidationErrorMessages = validationHelper.validate(authCredentials);
        
        // if there are no validation errors then try to get the user by email and password

        if ( credentialsValidationErrorMessages.isEmpty() ) {
        	User registeredUser = userDAO.checkRegisteredUser(authCredentials.getEmail(), authCredentials.getPassword());
        	if ( registeredUser != null ) {
        		LOGGER.info("Checking where to redirect");
        		// set session attributes and redirect to /home
        		request.getSession().setAttribute("user", registeredUser);
        		LOGGER.info("Have set user attr into the session");
        		request.getSession().setAttribute("user_role", registeredUser.getRole());
        		LOGGER.info("Have set role attr into the session " + registeredUser.getRole() );
        		if ( registeredUser.getRole().equals("customer")) {
        			LOGGER.info("Redirecting to home because user is customer");
        		response.sendRedirect(request.getContextPath() + "/home");
        		return;
        		} 
        		if ( registeredUser.getRole().equals("admin")) {
        			LOGGER.info("Redirecting to admin dashboard");
            		response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            		return;
            		}
        	} else  {
        		// no such user in db
        		credentialsValidationErrorMessages.add("Login and password you have provided doesnt match to any registered user.");
        		request.setAttribute("errorMessages", credentialsValidationErrorMessages);
            	request.getRequestDispatcher("WEB-INF/front/login_form.jsp").forward(request, response);
            	return;
        	}
        	
        } else {
        	// forward early
        	LOGGER.info("Form Validation is not ok. Forwarding to login page again");
        	LOGGER.info(credentialsValidationErrorMessages);
        	request.setAttribute("errorMessages", credentialsValidationErrorMessages);
        	request.getRequestDispatcher("WEB-INF/front/login_form.jsp").forward(request, response);
        	return;
        }
		
	}
	
	
}
