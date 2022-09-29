package logic;

import java.sql.SQLException;
import java.util.ArrayList;

import data.DataProducts;
import entities.Products;
import data.DataStores;
import entities.Stores;
import entities.InventoryItem;


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
			InventoryItem inventoryItem = dataProducts.getProductByStore(store, product);
			inventoryItem.setStock(inventoryItem.getStock() + quantity);
			dataProducts.updateStock(inventoryItem);
		}	
		
		public void decreaseStock(Stores store, Products product, Integer quantity) {
			InventoryItem inventoryItem = dataProducts.getProductByStore(store, product);
			inventoryItem.setStock(inventoryItem.getStock() - quantity);
			dataProducts.updateStock(inventoryItem);
		}	
		
		public void createStock(Stores store, Products product, Integer quantity) {
			InventoryItem inventoryItem = new InventoryItem(store.getId(),product.getId(),quantity);
			dataProducts.createStock(inventoryItem);
		}
		
		

}
