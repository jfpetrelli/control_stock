package logic;

import java.sql.SQLException;
import java.util.ArrayList;

import data.DataCustomers;
import entities.Customers;
import entities.Users;

public class CustomersLogic {
	
	private DataCustomers dataCustomer = new DataCustomers();

	public ArrayList<Customers> getAll() {
		try {
			return dataCustomer.readAll();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public void create(Customers customer) {
		dataCustomer.create(customer);
	}
	
	public void remove(Customers customer) {
		dataCustomer.remove(customer);
	}
	
	public void update(Customers customer) {
		dataCustomer.update(customer);
	}
	
	public Customers getById(Integer id) {
		Customers customer = dataCustomer.getById(id);
		return customer;
	}
	
}
