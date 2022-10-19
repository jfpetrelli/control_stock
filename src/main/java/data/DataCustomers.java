package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Customers;
import entities.Products;
import entities.Stores;
import entities.Users;

public class DataCustomers {

		
	public ArrayList<Customers> readAll() throws SQLException{
		
		ArrayList<Customers> customers = new ArrayList<>();
		
		
		Statement stmt= DbConnector.getInstancia().getConn().createStatement();
		ResultSet	rs= stmt.executeQuery("select * from customers");
		if(rs!=null) {
			while(rs.next()) {
				Customers customer= new Customers();
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
	
	public Customers getById(Integer id) {
		Customers customer=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select id,name,surname,comercial_name,mail from customers where id=?"
					);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				customer = new Customers();
				customer.setId(rs.getInt("id"));
				customer.setName(rs.getString("name"));
				customer.setSurnarme(rs.getString("surname"));
				customer.setComercial_name(rs.getString("comercial_name"));
				customer.setMail(rs.getString("mail"));
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
		return customer;
	}
	
	public void create(Customers customer) {
		
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into customers(name, surname, comercial_name, mail) values(?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			
			stmt.setString(1, customer.getName());
			stmt.setString(2, customer.getSurnarme());
			stmt.setString(3, customer.getComercial_name());
			stmt.setString(4, customer.getMail());

			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
            	customer.setId(keyResultSet.getInt(1));
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
	
	public void remove(Customers customer) {
		
		PreparedStatement stmt= null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from customers where id = ?"
							);
			
			stmt.setInt(1, customer.getId());

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
	
	public void update(Customers customer) {
		PreparedStatement stmt = null;			
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
							"UPDATE customers SET name = ?, surname = ? , comercial_name = ? , mail = ? where id = ?"
			);

			stmt.setString(1, customer.getName());
			stmt.setString(2, customer.getSurnarme());
			stmt.setString(3, customer.getComercial_name());
			stmt.setString(4, customer.getMail());
			stmt.setInt(5, customer.getId());
			
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
