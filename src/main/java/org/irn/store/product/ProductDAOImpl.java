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
import org.irn.store.util.PaginationHelper;

public class ProductDAOImpl implements ProductDAO {

private static final Logger LOGGER = LogManager.getLogger(ProductDAOImpl.class);
    private final String INSERT_PRODUCT_SQL = "insert into product (type, brand, model, material, color, image, price, category_id) VALUES (?,?,?,?,?,?,?,?)";
    private final String UPDATE_PRODUCT_BY_ID_SQL = "update product set type=?, brand=?, model=?, material=?, color=?, image=?, price=?, category_id=? where id=?";
    private final String GET_PRODUCTS_BY_CATEGORY_SQL = "select * from category inner join "
    		+ "product on category.id=product.category_id where category.id=?";
    private final String GET_PRODUCTS_BY_ID_SQL = "select * from product where id=?";
    private final String GET_TOTAL_RECORDS_SQL = "select count(*) as total_records from product where category_id=?";
    private final String DELETE_PRODUCT_BY_ID_SQL = "delete from product where product.id=?";
    
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

				savedProduct = product;
			} else {
				LOGGER.info("Error. New product was not saved to database");
			}
		} catch (SQLException e) {
			LOGGER.error("Some SQL error happened while creating new product");
			e.printStackTrace();
		} finally {
			closeResourcesWithPreparedStatement(conn, stmt, rs);
		}

		return savedProduct;
		
	}

	@Override
	public Product update(Product product) {
		Connection conn = null;
		PreparedStatement stmt= null;
		ResultSet rs = null;
		Product savedProduct = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(UPDATE_PRODUCT_BY_ID_SQL);
			stmt.setString(1, product.getType());
			stmt.setString(2, product.getBrand());
			stmt.setString(3, product.getModel());
			stmt.setString(4, product.getMaterial());
			stmt.setString(5, product.getColor());
			stmt.setString(6, product.getImage());
			stmt.setBigDecimal(7,product.getPrice());
			stmt.setInt(8, product.getCategoryId());
			stmt.setInt(9, product.getId());
			
			int affectedRows = stmt.executeUpdate();
			
			if (affectedRows > 0) {
				LOGGER.info("Product was updated");
				savedProduct = getById(product.getId());
			} else {
				LOGGER.info("Error. Product was not updated");
			}
		} catch (SQLException e) {
			LOGGER.error("Some SQL error happened while updating product");
			e.printStackTrace();
		} finally {
			closeResourcesWithPreparedStatement(conn, stmt, rs);
		}

		return savedProduct;
	}
	
	// returns empty List when no results
	@Override
	public List<Product> getByCategory(Integer category_id) {
		Connection conn = null;
		PreparedStatement stmt= null;
		ResultSet rs = null;
		List<Product> productsInCategory = new ArrayList<Product>();
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(GET_PRODUCTS_BY_CATEGORY_SQL);
			LOGGER.info(stmt.toString());
            stmt.setInt(1, category_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
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

	@Override
	public Product getById(Integer productId) {
		Connection conn = null;
		PreparedStatement stmt= null;
		ResultSet rs = null;
		Product product = new Product();
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(GET_PRODUCTS_BY_ID_SQL);
            stmt.setInt(1, productId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				product.setId(rs.getInt(1));
				product.setType(rs.getString(2));
				product.setBrand(rs.getString(3));
				product.setModel(rs.getString(4));
				product.setMaterial(rs.getString(5));
				product.setColor(rs.getString(6));
				product.setImage(rs.getString(7));
				product.setPrice(rs.getBigDecimal(8));
				product.setCategoryId(rs.getInt(11));
			}

		} catch (SQLException e) {
			LOGGER.error("Some SQL error happened while collecting product by id " + productId);
			e.printStackTrace();
		} finally {
			closeResourcesWithPreparedStatement(conn, stmt, rs);
		}
        LOGGER.info("Product by id " + productId + " is " + product);
		return product;
	}

	@Override
	public Integer getTotalRecords(Integer categoryId) {
		Connection conn = null;
		PreparedStatement stmt= null;
		ResultSet rs = null;
		Integer numberOfRecords = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(GET_TOTAL_RECORDS_SQL);
			stmt.setInt(1, categoryId);
			rs = stmt.executeQuery();
			rs.next();
			numberOfRecords = rs.getInt(1);
		} catch (SQLException e) {
			LOGGER.error("Some SQL error happened while getting total number of records in 'product' table ");
			e.printStackTrace();
		} finally {
			closeResourcesWithStatement(conn, stmt, rs);
		}

		return numberOfRecords;
	}

	@Override
	public List<Product> getByCategoryAndPageNumber(Integer categoryId, String sqlToAdd) {
		Connection conn = null;
		PreparedStatement stmt= null;
		ResultSet rs = null;
		List<Product> productsInCategory = new ArrayList<Product>();
		try {
			conn = dataSource.getConnection();
			String sql = GET_PRODUCTS_BY_CATEGORY_SQL + sqlToAdd;
			stmt = conn.prepareStatement(sql);
			LOGGER.info("SQL "+GET_PRODUCTS_BY_CATEGORY_SQL + sqlToAdd);
            stmt.setInt(1, categoryId);
            
			LOGGER.info(stmt.toString());
			rs = stmt.executeQuery();
			while (rs.next()) {
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

			LOGGER.info("Checked for presence of the products in category " + categoryId);
			LOGGER.info(productsInCategory.size());

		} catch (SQLException e) {
			LOGGER.error("Some SQL error happened while collecting products in category " + categoryId);
			e.printStackTrace();
		} finally {
			closeResourcesWithPreparedStatement(conn, stmt, rs);
		}
		return productsInCategory;
	}

	@Override
	public void delete(Integer productId) {
		Connection conn = null;
		PreparedStatement stmt= null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(DELETE_PRODUCT_BY_ID_SQL);
			stmt.setInt(1, productId);
			int affectedRows = stmt.executeUpdate();
			
			if (affectedRows > 0) {
				LOGGER.info("Product with id " + productId + "was deleted from database");
			} else {
				LOGGER.info("Error. Product was not deleted from database");
			}
		} catch (SQLException e) {
			LOGGER.error("Some SQL error happened while deleting product");
			e.printStackTrace();
		} finally {
			closeResourcesWithPreparedStatement(conn, stmt, rs);
		}

	}

	

	

}
