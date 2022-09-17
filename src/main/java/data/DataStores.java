package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Stores;

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
}
