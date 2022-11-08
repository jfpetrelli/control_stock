package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import entities.ListSales;
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
	
	public ArrayList<ListSales> listSales(String customer, String store, LocalDateTime desde, LocalDateTime hasta) {
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<ListSales> ls = new ArrayList<>();

		try {
			
			String sql = "SELECT s.id "
					+ "	,c.name "
					+ "	,p.detail "
					+ "	,sto.detail AS storage "
					+ "	,loc.city "
					+ "	,sd.quantity "
					+ "	,p.price AS unit_price "
					+ "	,sd.quantity * p.price AS price "
					+ "FROM control_stock.sales s "
					+ "INNER JOIN control_stock.sales_details sd ON s.id = sd.sale_id "
					+ "INNER JOIN control_stock.customers c ON s.customer = c.id "
					+ "LEFT JOIN control_stock.products p ON sd.product_id = p.id "
					+ "LEFT JOIN control_stock.stores sto ON s.store = sto.id "
					+ "LEFT JOIN control_stock.locations loc ON loc.id = sto.location_id "
					+ "WHERE 1 = 1 "
					+ " AND s.date between '" + desde + "' and '" + hasta + "' "
					+ (customer == null ? "" : " AND s.customer = " + customer)
					+ (store == null ? "" : " AND s.store = " + store);
			
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery(sql);
			
			System.out.println(sql);
			while(rs.next()) {
				
				ListSales l = new ListSales();
				
				l.setId(rs.getInt("id"));
				l.setCustomer(rs.getString("name"));
				l.setProduct(rs.getString("detail"));
				l.setStore(rs.getString("storage"));
				l.setQuantity(rs.getInt("quantity"));
				l.setUnit_price(rs.getDouble("unit_price"));
				l.setPrice(rs.getDouble("price"));
				
				ls.add(l);
				
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
		return ls;
	}
}
