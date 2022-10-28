package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Location;
import entities.Stores;


public class DataLocations {

	
	public ArrayList<Location> readAll(){
		try {
			ArrayList<Location> locations = new ArrayList<>();
			
			Statement stmt= DbConnector.getInstancia().getConn().createStatement();
			ResultSet	rs= stmt.executeQuery("select * from locations");
			if(rs!=null) {
				while(rs.next()) {
					Location location = new Location();
					location.setId(rs.getInt("id"));
					location.setCity(rs.getString("city"));
					location.setState(rs.getString("state"));
					location.setZipcode(rs.getString("zipcode"));
					locations.add(location);
					}
				}
		
			rs.close();
			stmt.close();
			DbConnector.getInstancia().releaseConn();
			return locations;
		} catch (Exception e) {
			return null;	
		}			
	}		
	
	public Location getById(Integer id) {
		Location location =null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select id,city,state,zipcode from locations where id=?"
					);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				location = new Location();
				location.setId(rs.getInt("id"));
				location.setCity(rs.getString("city"));
				location.setState(rs.getString("state"));
				location.setZipcode(rs.getString("zipcode"));
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
		return location;
	}	

	public void create(Location location) {
		
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into locations(city, state, zipcode) values(?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			
			stmt.setString(1, location.getCity());
			stmt.setString(2, location.getState());
			stmt.setString(3, location.getZipcode());

			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
            	location.setId(keyResultSet.getInt(1));
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
	
	public void update(Location location) {
		PreparedStatement stmt = null;			
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
							"UPDATE locations SET city = ?, state = ?, zipcode = ? where id = ?"
			);

			stmt.setString(1, location.getCity());
			stmt.setString(2, location.getState());
			stmt.setInt(4, location.getId());
			stmt.setString(3, location.getZipcode());
			
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


