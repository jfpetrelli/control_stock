package ui;
import java.time.LocalDateTime;


import entities.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		probarVenta();
	}
	
	
	public static void probarVenta() { // Test para ver las entidades
		Customers customer = new Customers();
		Stores store = new Stores();
		Sales sale = new Sales();
		
		customer.setId(1);
		customer.setAddres("Salta 326");
		customer.setCompany_name("Totti SRL");
		customer.setMail("jfpetrelli@gmail.com");
		customer.setName("Juan Franco");
		customer.setSurnarme("Petrelli");
		
		sale.setCustomer(customer);
		
		store.setAddress("Rivadia 123");
		store.setDetail("Almacen 1");
		store.setId(1);
		
		sale.setStore(store);
		
		sale.setId(1);
		sale.setDatetime(LocalDateTime.now());
		
		Products product1 = new Products();		
		product1.setId(1);
		product1.setDetail("Articulo 1");
		product1.setPrice(100);
		product1.setStock(1);
		
		Products product2 = new Products();
		product2.setId(2);
		product2.setDetail("Articulo 2");
		product2.setPrice(100.5);
		product2.setStock(3);
	
		Products product3 = new Products();	
		product3.setId(3);
		product3.setDetail("Articulo 3");
		product3.setPrice(50.7);
		product3.setStock(7);
		
		sale.addProduct(product1);
		sale.addProduct(product2);
		sale.addProduct(product3);

		System.out.println(sale.getTotal());
	}

}
