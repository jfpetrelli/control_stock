package logic;

import java.sql.SQLException;
import java.util.ArrayList;

import data.DataRoles;
import entities.Roles;


public class RolesLogic {
	
	DataRoles dataRol = new DataRoles();
	
	
	public ArrayList<Roles> getAll() {
		
		try {
			return dataRol.readAll();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void create(Roles rol) {
		dataRol.create(rol);
	}
	
	public boolean remove(Roles rol) {
		return dataRol.remove(rol);
	}
	
	public void update(Roles rol) {
		dataRol.update(rol);
	}
	
	public Roles getById(Integer id) {
		Roles rol = dataRol.getById(id);
		return rol;
	}

}
