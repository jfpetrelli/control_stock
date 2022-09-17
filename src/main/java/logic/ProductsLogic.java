package logic;

import java.sql.SQLException;
import java.util.ArrayList;

import data.DataProducts;
import entities.Products;


public class ProductsLogic {
	
		private DataProducts dataProducts = new DataProducts();

		public ArrayList<Products> getAll() {
			try {
				return dataProducts.readAll();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				e.printStackTrace();
				return null;
			}

		}

}
