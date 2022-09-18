package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Products;

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


}


