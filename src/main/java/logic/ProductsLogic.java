package logic;

import java.sql.SQLException;
import java.util.ArrayList;

import data.DataProducts;
import entities.Customers;
import entities.Products;
import data.DataStores;
import entities.Stores;

public class ProductsLogic {
	
		private DataProducts dataProducts = new DataProducts();
		private DataStores dataStore = new DataStores();

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
		
		public boolean belongsToStore(String store, Products product) {
			Integer storeId = Integer.parseInt(store);
			
			ArrayList<Products> products = null;
			try {
				products = dataProducts.readProductsStores(storeId);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(Products prod : products) {
				if(prod.getId() == product.getId()) {
					return true;
				}
			}
			return false;
			
		}
		
		public void create(Products product) {
			dataProducts.create(product);
		}
		
		public void remove(Products product) {
			dataProducts.remove(product);
		}
		
		public void update(Products product) {
			dataProducts.update(product);
		}
		
		public Products getById(Integer id) {
			Products product = dataProducts.getById(id);
			return product;
		}
	
}
