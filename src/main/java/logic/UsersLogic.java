package logic;

import java.sql.SQLException;
import java.util.ArrayList;

import data.DataUsers;
import entities.Users;


public class UsersLogic {
	
	DataUsers dataUser = new DataUsers();
	
	public ArrayList<Users> getAll() throws SQLException {
		
		return dataUser.readAll();
	
	}
	
	public void create(Users user) {
		dataUser.create(user);
	}
	
	public void remove(Users user) {
		dataUser.remove(user);
	}
	
	public void update(Users user) {
		dataUser.update(user);
	}
	
	public Users getById(Integer id) {
		Users user = dataUser.getById(id);
		return user;
	}
}
