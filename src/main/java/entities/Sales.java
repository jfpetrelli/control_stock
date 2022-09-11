package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Sales {

	private int id;
	private LocalDateTime datetime;
	private Stores store;
	private Customers customer;
	private ArrayList<Products> products = new ArrayList<>();

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getDatetime() {
		return datetime;
	}
	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}
	public double getTotal() {
		double total= 0;
		for(Products product: products) {
			total += product.getPrice() * product.getStock();
		}
		return total;
	}

	public Stores getStore() {
		return store;
	}
	public void setStore(Stores store) {
		this.store = store;
	}
	public Customers getCustomer() {
		return customer;
	}
	public void setCustomer(Customers customer) {
		this.customer = customer;
	}
	
	public void addProduct(Products product) {
		this.products.add(product);
	}
	
	public ArrayList<Products> getProducts(){
		return this.products;
	}
	
}
