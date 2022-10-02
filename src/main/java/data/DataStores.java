package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Products;
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
	
	public Stores getById(Integer id) {
		Stores store=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select id,detail,address from stores where id=?"
					);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				store = new Stores();
				store.setId(rs.getInt("id"));
				store.setDetail(rs.getString("detail"));
				store.setAddress(rs.getString("address"));
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
}
