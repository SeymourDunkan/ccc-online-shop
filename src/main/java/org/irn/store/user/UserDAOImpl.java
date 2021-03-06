package org.irn.store.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.irn.store.admin.UserListParams;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAOImpl implements UserDAO {
	private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);

	
	
	private DataSource dataSource;

    public UserDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private final String GET_ALL_USERS_SQL = "select * from users";
	private final String GET_ALL_CUSTOMERS_SQL = "select * from users where role!='admin'";
	private final String GET_USERS_BY_EMAIL_SQL = "select * from users where email=?";
	private final String INSERT_USER_SQL = "insert into users (email, password, first_name, last_name, role, blocked) VALUES (?,?,?,?,?,?)";
	private final String UPDATE_USER_BLOCKED_STATUS_SQL = "update users set blocked=? where user_id=?";
	private static final String TOTAL_USER_RECORDS_SQL = "select count(*) from users";
    
	@Override
	public User create(User user) {
		LOGGER.info("UserDAO: creating user");
		Connection conn = null;
		PreparedStatement stmt= null;
		ResultSet rs = null;
		User savedUser = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(INSERT_USER_SQL);
			stmt.setString(1, user.getEmail());
			stmt.setString(2, hashPassword(user.getPassword()));
			stmt.setString(3, user.getFirstName());
			stmt.setString(4, user.getLastName());
			stmt.setString(5, user.getRole());
			stmt.setString(6, user.getBlocked());
			int affectedRows = stmt.executeUpdate();
			if (affectedRows > 0) {
				savedUser = getByEmail(user.getEmail());
			} else {
				LOGGER.info("Error. New user was not saved to database");
			}
		} catch (SQLException e) {
			LOGGER.error("Some SQL error happened while adding new user");
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}

		return savedUser;
	}


	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getByEmail(String email) {
		User user = null;
		Connection conn = null;
		PreparedStatement stmt= null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(GET_USERS_BY_EMAIL_SQL);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			LOGGER.info("Checking if email exists");
			while (rs.next()) {
				        LOGGER.info("Email exists");
						user = new User(rs.getInt("user_id"), rs.getString("email"), 
								rs.getString("password"), rs.getString("first_name"), 
								rs.getString("last_name"), rs.getString("role"),
								rs.getString("blocked"));
					
			}
		} catch (SQLException e) {
			LOGGER.error("Could not get User by email");
		} finally {
			closeResources(conn, stmt, rs);
		}

		return user;
	}

	@Override
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAll() {
		List<User> usersList = new ArrayList<User>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			
			conn = this.dataSource.getConnection();
			System.out.println(conn);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(GET_ALL_USERS_SQL);
			
			while (rs.next()) {
				usersList.add(
						new User(rs.getInt("user_id"), rs.getString("email"), 
								rs.getString("password"), rs.getString("first_name"), 
								rs.getString("last_name"), rs.getString("role"),
								rs.getString("blocked")));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if ( rs != null) {
					rs.close();
				}
				if ( stmt != null) {
					stmt.close();
				}
				if ( conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return null;
	}
		

	// true if email exist already
	public boolean emailExists(String email) {
		User user = getByEmail(email);
		return user != null;
	}
	
	@Override
	public User checkRegisteredUser(String email, String password) {
		User user = null;
		Connection conn = null;
		PreparedStatement stmt= null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(GET_USERS_BY_EMAIL_SQL);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			LOGGER.info("Getting user by email" + email);
			while (rs.next()) {
						user = new User(rs.getInt("user_id"), rs.getString("email"), 
								rs.getString("password"), rs.getString("first_name"), 
								rs.getString("last_name"), rs.getString("role"),
								rs.getString("blocked"));
			}
			LOGGER.info("Found this user" + user);
			LOGGER.info("Checking password");
			if ( user != null ) {
				if ( !checkPasswordHashMatch(password, user.getPassword())) {user = null;}
			}
			LOGGER.info("user is " + user);
		} catch (SQLException e) {
			LOGGER.error("Could not get User by email");
			e.printStackTrace();
			
		} finally {
			closeResources(conn, stmt, rs);
		}

		return user;
	}
    
	private String hashPassword(String plainTextPassword) {
		String hashed = BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
		return hashed;
	}
	
	private boolean checkPasswordHashMatch(String plainTextPassword, String hashedPassword) {
		// Check that an unencrypted password matches one that has
		// previously been hashed
		if (BCrypt.checkpw(plainTextPassword, hashedPassword)) {
			System.out.println("It matches");
			return true;
		} else {
			System.out.println("It does not match");
			return false;
		}
	}
	
	private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
		try {
			if ( rs != null) {
				rs.close();
			}
			if ( stmt != null) {
				stmt.close();
			}
			if ( conn != null) {
				conn.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}


	@Override
	public void updateBlocked(Integer userId, String blocked) {
		//set "blocked=YES || NO"
		Connection conn = null;
		PreparedStatement stmt= null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(UPDATE_USER_BLOCKED_STATUS_SQL);
			stmt.setString(1, blocked);
			stmt.setInt(2, userId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Something went wrong while updating blocked status");
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
	}


	@Override
	public List<User> getUsers(String paginationSql, UserListParams params) {
		Connection conn = null;
		PreparedStatement stmt= null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<User>();
		try {
			conn = dataSource.getConnection();
			String sql = GET_ALL_CUSTOMERS_SQL + paginationSql;
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				userList.add(
						new User(rs.getInt("user_id"), rs.getString("email"), 
								rs.getString("password"), rs.getString("first_name"), 
								rs.getString("last_name"), rs.getString("role"),
								rs.getString("blocked")));
			}
		} catch (SQLException e) {
			LOGGER.error("Something went wrong while getting list of users");
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		return userList;
	}
	
	public Integer getTotalRecords() {
		Connection conn = null;
		PreparedStatement stmt= null;
		ResultSet rs = null;
		Integer numberOfRecords = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(TOTAL_USER_RECORDS_SQL);
			rs = stmt.executeQuery();
			rs.next();
			numberOfRecords = rs.getInt(1);
		} catch (SQLException e) {
			LOGGER.error("Some SQL error happened while getting total number of records of users");
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}

		return numberOfRecords;
	}

}
