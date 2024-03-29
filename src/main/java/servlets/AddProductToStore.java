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
 * Servlet implementation class AddProductToStore
 */
@WebServlet("/AddProductToStore")
public class AddProductToStore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductsLogic productLogic = new ProductsLogic();
	DataProducts dataProduct = new DataProducts();
	DataStores dataStore = new DataStores();
	ArrayList<Products> products = new ArrayList<>();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductToStore() {
        super();
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
		
		
		//Logica para Agregar producto
		if(request.getParameter("store") == null) {
			response.sendRedirect("/control_stock/500.html");
			return;
		}
		try {
			Integer store_id = Integer.parseInt(request.getParameter("store"));
			Stores store = dataStore.getById(store_id);
			if(store == null) {
				response.sendRedirect("/control_stock/500.html");
				return;
			}
			products = productLogic.getAll();
		    request.setAttribute("store", store);
		    request.setAttribute("products", products);		     
		} catch (Exception e) {
			response.sendRedirect("/control_stock/500.html");
			return;
		}

		request.getRequestDispatcher("WEB-INF/addProductToStore.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
