package org.irn.store.product;

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

public class ProductDAOImpl implements ProductDAO {

private static final Logger LOGGER = LogManager.getLogger(ProductDAOImpl.class);
    private final String INSERT_PRODUCT_SQL = "insert into product (type, brand, model, material, color, image, price, category_id) VALUES (?,?,?,?,?,?,?,?)";
    private final String GET_PRODUCTS_BY_CATEGORY_SQL = "select * from category inner join "
    		+ "product on category.id=product.category_id where category.id=?";
	private DataSource dataSource;

	public ProductDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Product create(Product product) {
		Connection conn = null;
		PreparedStatement stmt= null;
		ResultSet rs = null;
		Product savedProduct = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(INSERT_PRODUCT_SQL);
			stmt.setString(1, product.getType());
			stmt.setString(2, product.getBrand());
			stmt.setString(3, product.getModel());
			stmt.setString(4, product.getMaterial());
			stmt.setString(5, product.getColor());
			stmt.setString(6, product.getImage());
			stmt.setBigDecimal(7,product.getPrice());
			stmt.setInt(8, product.getCategoryId());
			
			int affectedRows = stmt.executeUpdate();
			
			if (affectedRows > 0) {
				LOGGER.info("New product was saved to database");
				// TODO Auto-generated method stub
				savedProduct = product;
			} else {
				LOGGER.info("Error. New product was not saved to database");
			}
		} catch (SQLException e) {
			LOGGER.error("Some SQL error happened while adding new user");
			e.printStackTrace();
		} finally {
			closeResourcesWithPreparedStatement(conn, stmt, rs);
		}

		return savedProduct;
		
	}

	@Override
	public Product update(Product product) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// returns empty List when no results
	@Override
	public List<Product> getByCategory(Integer category_id) {
		Connection conn = null;
		PreparedStatement stmt= null;
		ResultSet rs = null;
		List<Product> productsInCategory = new ArrayList<Product>();
		try {
			LOGGER.info("dataSource is " + dataSource);
			conn = dataSource.getConnection();
			LOGGER.info(conn);
			stmt = conn.prepareStatement(GET_PRODUCTS_BY_CATEGORY_SQL);
			LOGGER.info(stmt.toString());
            stmt.setInt(1, category_id);
			rs = stmt.executeQuery();
			LOGGER.info(rs.toString());
			while (rs.next()) {
				LOGGER.info("Checking for results");
				Product product = new Product();
				product.setId(rs.getInt(3));
				product.setType(rs.getString(4));
				product.setBrand(rs.getString(5));
				product.setModel(rs.getString(6));
				product.setMaterial(rs.getString(7));
				product.setColor(rs.getString(8));
				product.setImage(rs.getString(9));
				product.setPrice(rs.getBigDecimal(10));
				product.setCategoryId(rs.getInt(1));
				
				productsInCategory.add(product);
			}

			LOGGER.info("Checked for presence of the products in category " + category_id);
			LOGGER.info(productsInCategory);

		} catch (SQLException e) {
			LOGGER.error("Some SQL error happened while collecting products in category " + category_id);
			e.printStackTrace();
		} finally {
			closeResourcesWithPreparedStatement(conn, stmt, rs);
		}

		return productsInCategory;
	}
	
	private void closeResourcesWithPreparedStatement(Connection conn, PreparedStatement stmt, ResultSet rs) {
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
	
	private void closeResourcesWithStatement(Connection conn, Statement stmt, ResultSet rs) {
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

	

	

}
