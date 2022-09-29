package entities;

public class InventoryItem {
	
	private int product_id;
	private int store_id;
	private int stock;
	
	
	
	public InventoryItem(int product_id, int store_id, int stock) {
		super();
		this.product_id = product_id;
		this.store_id = store_id;
		this.stock = stock;
	}
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getStore_id() {
		return store_id;
	}
	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	

}
