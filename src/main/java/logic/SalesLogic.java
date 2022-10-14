package logic;

import java.util.ArrayList;
import entities.Products;

public class SalesLogic {

	
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
		
		for (Products product: products) {
			
			if(product.getId() == Integer.parseInt(id_product)) {
				
				product.setStock(Integer.parseInt(quantity));
				
				return product;
				
			}
			
		}
		
		return null;

	}
	
}
