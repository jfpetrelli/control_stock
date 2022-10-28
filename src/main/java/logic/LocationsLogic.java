package logic;

import java.sql.SQLException;
import java.util.ArrayList;

import data.DataLocations;
import data.DataStores;
import entities.Location;
import entities.Stores;

public class LocationsLogic {

	private DataLocations dataLocation = new DataLocations();

	public ArrayList<Location> getAll() {
		return dataLocation.readAll();				
	}
	
	public void create(Location location) {
		dataLocation.create(location);
	}
	
	
	public Location getById(Integer location_id) {
		Location location = dataLocation.getById(location_id);
		return location;
	}
	
	public void update(Location location) {
		dataLocation.update(location);
	}
}
