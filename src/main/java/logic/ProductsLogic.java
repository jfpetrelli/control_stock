package logic;

import java.sql.SQLException;
import java.util.ArrayList;

import data.DataProducts;
import entities.Products;
import data.DataStores;
import entities.Stores;

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
		
		public ArrayList<Products> getProductsStores(int store_id) {
			try {
				return dataProducts.readProductsStores(store_id);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				e.printStackTrace();
				return null;
			}
		}
		
		public void increaseStock(Stores store, Products product, Integer quantity) {
			Products p = dataProducts.getProductByStore(store, product);
			Integer stock = p.getStores().get(store);
			p.getStores().put(store, stock + quantity);					
			dataProducts.updateStock(p,store);
		}	
		
		public void decreaseStock(Stores store, Products product, Integer quantity) {
			Products p = dataProducts.getProductByStore(store, product);
			Integer stock = p.getStores().get(store);
			p.getStores().put(store, stock - quantity);	
			dataProducts.updateStock(p,store);
		}	
		
		public void createStock(Stores store, Products product, Integer quantity) {
			dataProducts.createStock(store,product,quantity);
		}
	
}
