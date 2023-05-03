package data;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Users;

public class DataUsers {
	
	public ArrayList<Users> readAll(){
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Users> users = new ArrayList<>();
		DataRoles dataRoles = new DataRoles();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select * from users");
			if(rs!=null) {
				while(rs.next()) {
					Users u=new Users();
					u.setId(rs.getInt("id"));
					u.setName(rs.getString("name"));
					u.setLastname(rs.getString("surname"));
					u.setEmail(rs.getString("mail"));
					u.setUsername(rs.getString("user_name"));
					u.setPassword(rs.getString("password"));
					u.setRol(dataRoles.getById(rs.getInt("rol")));
					users.add(u);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return users;
	}
	
	public Users getById(Integer id) {
		Users user=null;
		DataRoles dataRoles = new DataRoles();
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select id,user_name,password,name,surname,mail,rol from users where id=?"
					);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				user = new Users();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setLastname(rs.getString("surname"));
				user.setEmail(rs.getString("mail"));
				user.setRol(dataRoles.getById(rs.getInt("rol")));

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
		return user;
	}
	
	public void create(Users user) {
		
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into users(user_name, name, surname, mail, password, rol) values(?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getName());
			stmt.setString(3, user.getLastname());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getPassword());
			stmt.setInt(6, user.getRol().getId());

			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
            	user.setId(keyResultSet.getInt(1));
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
	
	public void remove(Users user) {
		
		PreparedStatement stmt= null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from users where id = ?"
							);
			
			stmt.setInt(1, user.getId());

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
	
	public void update(Users user) {
		PreparedStatement stmt = null;			
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
							"UPDATE users SET user_name = ?, password = ?, name = ?, surname = ?, mail = ? , rol = ? where id = ?"
			);

			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getName());
			stmt.setString(4, user.getLastname());
			stmt.setString(5, user.getEmail());
			stmt.setInt(6, user.getRol().getId());
			stmt.setInt(7, user.getId());
			
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
	
	public Users Validar(String email,String password) {
		Users user=null;
		DataRoles dataRoles = new DataRoles();
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			
			if (DbConnector.getInstancia().getConn() ==null) return user;
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from users where mail = ? and password = ?"
					);
			stmt.setString(1, email);
			stmt.setString(2,password);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				user = new Users();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setLastname(rs.getString("surname"));
				user.setEmail(rs.getString("mail"));
				user.setRol(dataRoles.getById(rs.getInt("rol")));

			}
		} catch (SQLException e) {
			return user;
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				//DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				return user;
			}
		}		
		return user;
	}
	
}
