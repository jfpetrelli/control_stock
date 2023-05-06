package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
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
import logic.StoresLogic;

/**
 * Servlet implementation class Stock
 */
@WebServlet("/Stock")
public class Stock extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductsLogic productLogic = new ProductsLogic();
	StoresLogic storeLogic = new StoresLogic();
	DataProducts dataProduct = new DataProducts();
	DataStores dataStore = new DataStores();
	ArrayList<Stores> stores = new ArrayList<>();
	ArrayList<Products> products = new ArrayList<>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Stock() {
        super();
        stores = storeLogic.getAll();
        products = productLogic.getAll();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
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
		
		stores = storeLogic.getAll();
		if(stores == null) {
			String error = "Error al consultar el stock de los depósitos.";
			request.setAttribute("error", error);
			request.getRequestDispatcher("WEB-INF/stores_stock.jsp").forward(request, response);
		}
		request.setAttribute("stores", stores);
		request.getRequestDispatcher("WEB-INF/stores_stock.jsp").forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			Stores store = storeLogic.getById(Integer.parseInt(request.getParameter("store")));		
			Integer store_id = Integer.parseInt(request.getParameter("store"));
			
			request.setAttribute("products", productLogic.getProductsStores(store_id));
			request.setAttribute("store", store);
		} catch (Exception e) {
			response.sendRedirect("/control_stock/500.html");
			return;
		}


		request.getRequestDispatcher("WEB-INF/stock.jsp").forward(request, response);
	}

}
