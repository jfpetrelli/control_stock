package logic;

import java.sql.SQLException;
import java.util.ArrayList;

import data.DataCustomers;
import entities.Customers;

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
}
