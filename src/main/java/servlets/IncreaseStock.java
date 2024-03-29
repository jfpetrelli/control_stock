package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DataProducts;
import data.DataStores;
import entities.Products;
import entities.Stores;
import entities.Users;
import logic.ProductsLogic;

/**
 * Servlet implementation class IncreaseStock
 */
@WebServlet("/IncreaseStock")
public class IncreaseStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductsLogic productLogic = new ProductsLogic();
	DataProducts dataProduct = new DataProducts();
	DataStores dataStore = new DataStores();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncreaseStock() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		HttpSession sesion = request.getSession();
		Users user = (Users) sesion.getAttribute("usuario");
		
		//Chequeo si el usuario es Vendedor o no hay usuario
		if (user == null || user.getRol().getType().equalsIgnoreCase("Vendedor"))
			{
				sesion.invalidate();
				response.sendRedirect("/control_stock/404.html");
				return;
			}
		//Seteo nombre de usuario
		request.setAttribute("nombreUsuario", user.getName());
		
		//Logica para aumentar stock
		try {				
			Integer store_id = Integer.parseInt(request.getParameter("store"));
			Integer product_id = Integer.parseInt(request.getParameter("product"));
			Products product = null;
			Stores store = dataStore.getById(store_id);
			
			if(store == null) {
				response.sendRedirect("/control_stock/500.html");
				return;
			}
			
			ArrayList<Products> products = productLogic.getProductsStores(store_id);
			
			for(Products prod : products) {
				if(prod.getId() == product_id) {
					product = prod;
				}
			}
			
			if(product == null) {
				response.sendRedirect("/control_stock/500.html");
				return;
			}
						
		    request.setAttribute("product", product);
		    request.setAttribute("store", store);
		    
		} catch (Exception e) {
			response.sendRedirect("/control_stock/500.html");
			return;
		}

	     
		request.getRequestDispatcher("WEB-INF/increaseStock.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer productId = Integer.parseInt(request.getParameter("product"));
		Integer storeId = Integer.parseInt(request.getParameter("store"));
		Integer quantity = Integer.parseInt(request.getParameter("quantity"));
		
		if(quantity < 0) {
		    request.setAttribute("error", "La cantidad a agregar no puede ser igual o menor que 0.");			
			request.getRequestDispatcher("WEB-INF/increaseStock.jsp").forward(request, response);
			return;			
		}	
		
		Products product = dataProduct.getById(productId);
		Stores store = dataStore.getById(storeId);
		productLogic.increaseStock(store, product, quantity);
		// TODO Auto-generated method stub
		Stock stockServlet = new Stock();
		stockServlet.doGet(request, response);
	}

}
