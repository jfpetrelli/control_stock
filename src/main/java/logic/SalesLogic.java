package logic;

import java.util.ArrayList;

import data.DataCustomers;
import data.DataSales;
import entities.Products;
import entities.Sales;
import logic.ProductsLogic;;

public class SalesLogic {

	private DataSales dataSales = new DataSales();
	private ProductsLogic productsLogic = new ProductsLogic();
	
	
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
	
	public static Products setProduct(ArrayList<Products> products,String quantity, String id_product) {
		
		Products prod_selected = new Products();
		
		for (Products product: products) {
			
			if(product.getId() == Integer.parseInt(id_product)) {
				
				product.setStock(product.getStock() - Double.parseDouble(quantity));
				
				prod_selected.setId(product.getId());
				prod_selected.setDetail(product.getDetail());
				prod_selected.setPrice(product.getPrice());
				prod_selected.setStock(Double.parseDouble(quantity));
				return prod_selected;
				
				
			}
			
		}
		
		return null;

	}
	
	public String addSale(Sales sale) {
		
		
		
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
	
}
