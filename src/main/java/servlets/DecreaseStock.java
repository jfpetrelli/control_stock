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
 * Servlet implementation class DecreaseStock
 */
@WebServlet("/DecreaseStock")
public class DecreaseStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductsLogic productLogic = new ProductsLogic();
	DataProducts dataProduct = new DataProducts();
	DataStores dataStore = new DataStores();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DecreaseStock() {
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

		request.setAttribute("nombreUsuario", user.getName());
		
		try {
		Integer store_id = Integer.parseInt(request.getParameter("store"));
		Integer product_id = Integer.parseInt(request.getParameter("product"));
		Products product = null;
		Stores store = dataStore.getById(store_id);
		
		ArrayList<Products> products = productLogic.getProductsStores(store_id);
		
		if(store == null || products == null) {
			response.sendRedirect("/control_stock/500.html");
			return;
		}
		
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
		}
		catch (Exception e) {
			response.sendRedirect("/control_stock/500.html");
			return;
		}
	    
		request.getRequestDispatcher("WEB-INF/decreaseStock.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer productId = Integer.parseInt(request.getParameter("product"));
		Integer storeId = Integer.parseInt(request.getParameter("store"));
		Integer quantity = Integer.parseInt(request.getParameter("quantity"));			
		
		Products product = dataProduct.getById(productId);
		Stores store = dataStore.getById(storeId);
		
		Products p = dataProduct.getProductByStore(store, product);
		Integer stock = p.getStores().get(store);
		
		if(stock < quantity || quantity < 0) {
		    request.setAttribute("error", "El stock es insuficiente para la cantidad ingresada.");			
			request.getRequestDispatcher("WEB-INF/decreaseStock.jsp").forward(request, response);
			return;			
		}			
		
		productLogic.decreaseStock(store, product, quantity);
		
		Stock stockServlet = new Stock();
		stockServlet.doGet(request, response);
	}

}
