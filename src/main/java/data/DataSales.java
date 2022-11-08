package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import entities.Sales;
import entities.Stores;

public class DataSales {

	
	public Integer insertSale(Sales sale) {
		
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into sales(date, total, customer, store) values(?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			
			stmt.setDate(1, java.sql.Date.valueOf(sale.getDatetime().toLocalDate()));
			stmt.setDouble(2, sale.getTotal());
			stmt.setInt(3, sale.getCustomer().getId());
			stmt.setInt(4, sale.getStore().getId());

			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
            	sale.setId(keyResultSet.getInt(1));
            }

			
		}  catch (SQLException e) {
            e.printStackTrace();
            return null;
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            	return null;
            }
		}
		
		return sale.getId();
	}
	
	public String insertProductsQuantities(int id, int product, double stock, int pos){
		
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into sales_details(sale_id, product_id, quantity, pos) values(?,?,?,?)"
							);
			
			stmt.setInt(1, id);
			stmt.setInt(2, product);
			stmt.setDouble(3, stock);
			stmt.setInt(4, pos);

			stmt.executeUpdate();
			
		}  catch (SQLException e) {
            e.printStackTrace();
            return null;
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            	return null;

            }
		}
		
		return "OK";
	}
	
	
	public void updateProductsStores(int product, int store, double stock) {
		PreparedStatement stmt = null;			
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
							"UPDATE products_stores SET stock = ? where product_id = ? and store_id = ? "
			);

			stmt.setDouble(1, getStock(product, store) - stock);
			stmt.setInt(2, product);
			stmt.setInt(3, store);
			
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
	
	public double getStock(int product, int store) {
		
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select stock from products_stores where product_id =" + product + " and store_id = " + store);
			
			rs.next();
			return rs.getDouble("stock");
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
		return 0.0;
	}
	
	public void listSales(String customer, String store, LocalDateTime desde, LocalDateTime hasta) {
		
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select stock from products_stores where product_id =" + product + " and store_id = " + store);
			
			rs.next();
			return rs.getDouble("stock");
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
		return 0.0;
	}
}
