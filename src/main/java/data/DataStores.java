package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Products;
import entities.Stores;
import entities.Users;

public class DataStores {

	public ArrayList<Stores> readAll() throws SQLException{
		
		ArrayList<Stores> stores = new ArrayList<>();
		
		Statement stmt= DbConnector.getInstancia().getConn().createStatement();
		ResultSet	rs= stmt.executeQuery("select * from stores");
		if(rs!=null) {
			while(rs.next()) {
				Stores store=new Stores();
				store.setId(rs.getInt("id"));
				store.setDetail(rs.getString("detail"));
				store.setAddress(rs.getString("address"));
				stores.add(store);
				}
			}
			
			
		rs.close();
		stmt.close();
		DbConnector.getInstancia().releaseConn();
		return stores;
	}
	
	public Stores getById(Integer id) {
		Stores store=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select id,detail,address,location_id from stores where id=?"
					);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				store = new Stores();
				store.setId(rs.getInt("id"));
				store.setDetail(rs.getString("detail"));
				store.setAddress(rs.getString("address"));
				store.setLocation_id(rs.getInt("location_id"));
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
		return store;
	}
	
	public void create(Stores store) {
		
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into stores(address, detail, location_id) values(?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			
			stmt.setString(1, store.getAddress());
			stmt.setString(2, store.getDetail());
			stmt.setInt(3, store.getLocation_id());

			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
            	store.setId(keyResultSet.getInt(1));
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
	
	public void update(Stores store) {
		PreparedStatement stmt = null;			
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
							"UPDATE stores SET address = ?, detail = ?, location_id = ? where id = ?"
			);

			stmt.setString(1, store.getAddress());
			stmt.setString(2, store.getDetail());
			stmt.setInt(4, store.getId());
			stmt.setInt(3, store.getLocation_id());
			
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
	
	public void delete(Stores store) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from stores where id = ?"
							);
			
			stmt.setInt(1, store.getId());

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
