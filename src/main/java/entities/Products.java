package entities;

import java.util.HashMap;

public class Products {
	
	private int id;
	private String detail;
	private double price;
	private int stock;	
	private HashMap<Stores, Integer> stores = new HashMap<Stores, Integer>();
		
	public HashMap<Stores, Integer> getStores() {
		return stores;
	}
	public void setStores(HashMap<Stores, Integer> stores) {
		this.stores = stores;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public double getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
