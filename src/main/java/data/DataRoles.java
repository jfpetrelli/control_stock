package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Roles;

public class DataRoles {
	
	public ArrayList<Roles> readAll() throws SQLException{
		
		ArrayList<Roles> roles = new ArrayList<>();
		
		
		Statement stmt= DbConnector.getInstancia().getConn().createStatement();
		ResultSet	rs= stmt.executeQuery("select * from roles");
		if(rs!=null) {
			while(rs.next()) {
				Roles rol= new Roles();
				rol.setId(rs.getInt("id"));
				rol.setType(rs.getString("type"));				
				roles.add(rol);
				}
			}
			
			
		rs.close();
		stmt.close();
		DbConnector.getInstancia().releaseConn();
		return roles;
	}
	
	public Roles getById(Integer id) {
		Roles rol=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select id,type from roles where id=?"
					);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				rol = new Roles();
				rol.setId(rs.getInt("id"));
				rol.setType(rs.getString("type"));

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
		return rol;
	}
	
	public void create(Roles rol) {
		
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into roles(type) values(?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			
			stmt.setString(1, rol.getType());

			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
            	rol.setId(keyResultSet.getInt(1));
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
	
	public boolean remove(Roles rol) {
		
		PreparedStatement stmt= null;
		boolean deleted;
		
		try {
			stmt= DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from roles where id = ?"
							);
			
			stmt.setInt(1, rol.getId());

			stmt.executeUpdate();
			deleted = true;
			
		}  catch (SQLException e) {
			deleted = false;
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		return deleted;
	}
	
	public void update(Roles rol) {
		PreparedStatement stmt = null;			
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
							"UPDATE roles SET type = ? where id = ?"
			);

			stmt.setString(1, rol.getType());
			stmt.setInt(2, rol.getId());
			
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
