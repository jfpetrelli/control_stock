package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Customers;
import entities.Products;
import entities.Stores;

public class DataProducts {

	
	public ArrayList<Products> readAll() throws SQLException{
		
		ArrayList<Products> products = new ArrayList<>();
		
		
		Statement stmt= DbConnector.getInstancia().getConn().createStatement();
		ResultSet	rs= stmt.executeQuery("select * from products");
		if(rs!=null) {
			while(rs.next()) {
				Products product=new Products();
				product.setId(rs.getInt("id"));
				product.setDetail(rs.getString("detail"));
				product.setPrice(rs.getDouble("price"));
				products.add(product);
				}
			}
			
			
		rs.close();
		stmt.close();
		DbConnector.getInstancia().releaseConn();
		return products;
	}
	
	public Products getById(Integer id) {
		Products product=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select id,detail,price from products where id=?"
					);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				product = new Products();
				product.setId(rs.getInt("id"));
				product.setDetail(rs.getString("detail"));
				product.setPrice(rs.getDouble("price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return product;
	}
	
	public ArrayList<Products> readProductsStores(int store_id) throws SQLException{
		
		ArrayList<Products> products = new ArrayList<>();
		
		
		Statement stmt= DbConnector.getInstancia().getConn().createStatement();
		ResultSet	rs= stmt.executeQuery("SELECT p.id, p.detail, p.price, ps.stock FROM control_stock.products p " +
				 "inner join control_stock.products_stores ps on p.id = ps.product_id " +
				 "where store_id = " + store_id);
		if(rs!=null) {
			while(rs.next()) {
				Products product=new Products();
				product.setId(rs.getInt("id"));
				product.setDetail(rs.getString("detail"));
				product.setPrice(rs.getDouble("price"));
				product.setStock(rs.getInt("stock"));
				products.add(product);
				}
			}
			
			
		rs.close();
		stmt.close();
		DbConnector.getInstancia().releaseConn();
		return products;
	}
	

	public Products getProductByStore(Stores store, Products product) {		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select product_id,store_id,stock from products_stores where product_id=? and store_id=?"
					);
			stmt.setInt(1, product.getId());
			stmt.setInt(2, store.getId());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				product.getStores().put(store, rs.getInt("stock"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return product;		
	}
	
	public void updateStock(Products product, Stores store) {
		PreparedStatement stmt = null;			
			try {
				stmt = DbConnector.getInstancia().getConn().prepareStatement(
								"UPDATE products_stores SET stock = ? where product_id = ? and store_id = ?"
				);

				stmt.setInt(1, product.getStores().get(store));
				stmt.setInt(2, product.getId());
				stmt.setInt(3, store.getId());
				
				stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
	            try {	            	
	                if(stmt!=null) {
	                	stmt.close();
	                }	                
					DbConnector.getInstancia().releaseConn();	                
	            } catch (SQLException e) {
					// TODO Auto-generated catch block
	            	e.printStackTrace();
	            }
			}			
	}
	
	public void createStock(Stores store, Products product, Integer quantity) {
		
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into products_stores(product_id, store_id, stock) values(?,?,?)"
							);
			
			stmt.setInt(1, product.getId());
			stmt.setInt(2,store.getId());
			stmt.setInt(3, quantity);

			stmt.executeUpdate();
			
			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		
	}
	
	public void removeStock(Stores store, Products product) {
		
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from products_stores where product_id= ? and store_id= ?"
							);
			
			stmt.setInt(1, product.getId());
			stmt.setInt(2,store.getId());

			stmt.executeUpdate();
			
			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		
	}
	
	public void create(Products product) {
		
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into products(detail, price) values(?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			
			stmt.setString(1, product.getDetail());
			stmt.setDouble(2, product.getPrice());


			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
            	product.setId(keyResultSet.getInt(1));
            }

			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		
	}
	
	public void remove(Products product) {
		
		PreparedStatement stmt= null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from products where id = ?"
							);
			
			stmt.setInt(1, product.getId());

			stmt.executeUpdate();
			
			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		
	}
	
	public void update(Products product) {
		PreparedStatement stmt = null;			
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
							"UPDATE products SET detail = ?, price = ?  where id = ?"
			);

			stmt.setString(1, product.getDetail());
			stmt.setDouble(2, product.getPrice());
			stmt.setInt(3, product.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try {	            	
                if(stmt!=null) {
                	stmt.close();
                }	                
				DbConnector.getInstancia().releaseConn();	                
            } catch (SQLException e) {
				// TODO Auto-generated catch block
            	e.printStackTrace();
            }
		}
	}

}


