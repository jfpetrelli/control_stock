package logic;

import java.sql.SQLException;
import java.util.ArrayList;

import data.DataStores;
import entities.Stores;

public class StoresLogic {

	private DataStores dataStores = new DataStores();

	public ArrayList<Stores> getAll() {
		try {
			return dataStores.readAll();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}				
	}
	
	public void create(Stores store) {
		dataStores.create(store);
	}
	
	public void delete(Stores store) {
		dataStores.delete(store);
	}
	
	public void update(Stores store) {
		dataStores.update(store);
	}
	
	public Stores getById(Integer store_id) {
		Stores store = dataStores.getById(store_id);
		return store;
	}
}
