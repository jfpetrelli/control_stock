package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Location;


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

}


