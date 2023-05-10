package logic;

import java.time.LocalDateTime;
import java.util.ArrayList;


import data.DataSales;
import entities.ListSales;
import entities.Products;
import entities.Sales;


public class SalesLogic {

	private DataSales dataSales = new DataSales();
	private int pos=1;
	
	
	public static String quantityAddOK(ArrayList<Products> products,String quantity, String id_product) {
		
		
		for (Products product: products) {

			if(product.getId() == Integer.parseInt(id_product)) {
				
				if(product.getStock() < Integer.parseInt(quantity)) {
					return "La cantidad no puede superar el stock";
				}
				break;
			}

		}
		if(id_product.equals("")) return "Seleccione un Articulo";
		
		if(quantity.equals("0")) return "La cantidad no puede ser 0";
		
		return null;
	}
	
	public Products setProduct(ArrayList<Products> products,String quantity, String id_product) {
		
		Products prod_selected = new Products();
		
		for (Products product: products) {
			
			if(product.getId() == Integer.parseInt(id_product)) {
				
				product.setStock(product.getStock() - Double.parseDouble(quantity));
				
				prod_selected.setId(product.getId());
				prod_selected.setDetail(product.getDetail());
				prod_selected.setPrice(product.getPrice());
				prod_selected.setStock(Double.parseDouble(quantity));
				prod_selected.setPos(pos);
				pos++;
				return prod_selected;
				
				
			}
			
		}
		
		return null;

	}
	
	public String addSale(Sales sale) {
		
		
		if(sale.getProducts().isEmpty()) return "Ingrese al menos un artículo";
		
		Integer id = dataSales.insertSale(sale);
		
		if(id == null) {
			return " No se cargó la venta";
		}
		
		ArrayList<Products> products = new ArrayList<>();
		products = sale.getProducts();
		int count = 1;

		for(Products prod: products) {
			
			String ok = dataSales.insertProductsQuantities(id,prod.getId(), prod.getStock(), count);
			
			
			if(ok == null) return " No se cargó la venta";
			
			dataSales.updateProductsStores(prod.getId(), sale.getStore().getId(), prod.getStock());
			count++;
		}
		
		
		return "Se cargó la venta correctamente";

	}
	
	public void deleteItem(int pos, Sales sale, ArrayList<Products> products_list) {
		
		int id =0 ;
		double quantity = 0;

		ArrayList<Products> products = sale.getProducts();
		int i=0;
		for(Products prod: products) {
			
			if(prod.getPos() == pos) {
				
				sale.getProducts().remove(i);
				id = prod.getId();
				quantity = prod.getStock();
				break;
			}
			
			i++;
		}
		for(Products prod: products_list) {
			
			if(prod.getId() == id) {
				
				prod.setStock(prod.getStock() + quantity);
			}
			
			i++;
		}

	}
	
	public ArrayList<ListSales> listSales(String customer, String store, LocalDateTime desde, LocalDateTime hasta) {
		
		
		if(customer.isEmpty()) customer = null;
		if(store.isEmpty()) store = null;
		
		return dataSales.listSales(customer, store, desde, hasta);
		
		
	}

	public ArrayList<Sales> getSalesWithoutStatus() {
		// TODO Auto-generated method stub
		try {
			return dataSales.salesWithoutStatus();
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("Error en formato Date");
			return null;
		}

		
	}

	public ArrayList<ListSales> getSalesWithoutStatus(int sale_id) {
		// TODO Auto-generated method stub
		try {
			return dataSales.salesDetailsWithoutStatus(sale_id);
		} catch (Exception e) {
			System.out.println("Error al traer el detalle de la venta");
			return null;
		}
		
	}

	public String updateStatus(ArrayList<ListSales> listSales) {
		// TODO Auto-generated method stub
		String msg = "";
		for(ListSales listSale: listSales) {
			
			
			msg = dataSales.updateStatus(listSale.getId(),listSale.getPos(),listSale.getStatus());
			
			
		}
		
		return msg;
	}
	
}
