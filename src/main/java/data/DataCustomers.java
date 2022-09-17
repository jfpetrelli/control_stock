package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Customers;

public class DataCustomers {

		
	public ArrayList<Customers> readAll() throws SQLException{
		
		ArrayList<Customers> customers = new ArrayList<>();
		
		
		Statement stmt= DbConnector.getInstancia().getConn().createStatement();
		ResultSet	rs= stmt.executeQuery("select * from customers");
		if(rs!=null) {
			while(rs.next()) {
				Customers customer=new Customers();
				customer.setId(rs.getInt("id"));
				customer.setName(rs.getString("name"));
				customer.setSurnarme(rs.getString("surname"));
				customer.setComercial_name(rs.getString("comercial_name"));
				customer.setMail(rs.getString("mail"));					
				customers.add(customer);
				}
			}
			
			
		rs.close();
		stmt.close();
		DbConnector.getInstancia().releaseConn();
		return customers;
	}
}
