package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Products;
import entities.Stores;
import entities.InventoryItem;

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
	

	public InventoryItem getProductByStore(Stores store, Products product) {
		InventoryItem ii = null;
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
				ii = new InventoryItem(rs.getInt("product_id"),rs.getInt("store_id"),rs.getInt("stock"));
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
		return ii;		
	}
	
	public void updateStock(InventoryItem inventoryItem) {
		PreparedStatement stmt = null;			
			try {
				stmt = DbConnector.getInstancia().getConn().prepareStatement(
								"UPDATE products_stores SET stock = ? where product_id = ? and store_id = ?"
				);

				stmt.setInt(1, inventoryItem.getStock());
				stmt.setInt(2, inventoryItem.getProduct_id());
				stmt.setInt(3, inventoryItem.getStore_id());
				

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
	
	public void createStock(InventoryItem inventoryItem) {
		
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into products_stores(product_id, store_id, stock) values(?,?,?)"
							);
			
			stmt.setInt(1, inventoryItem.getProduct_id());
			stmt.setInt(2,inventoryItem.getStore_id());
			stmt.setInt(3, inventoryItem.getStock());

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

}


