package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Stores;
import entities.Users;
import logic.LocationsLogic;
import logic.StoresLogic;

/**
 * Servlet implementation class Store
 */
@WebServlet("/Store")
public class Store extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StoresLogic storeLogic = new StoresLogic();
	LocationsLogic locationLogic = new LocationsLogic();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Store() {
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
		
		
		//Logica para ver deposito
		if(request.getParameter("store") == null) {
			response.sendRedirect("/control_stock/500.html");
			return;
		}
		Integer store_id = Integer.parseInt(request.getParameter("store"));
		Stores store = storeLogic.getById(store_id);
		if(store == null) {
			response.sendRedirect("/control_stock/500.html");
			return;
		}
		entities.Location location = locationLogic.getById(store.getLocation_id());

		request.setAttribute("store", store);
		request.setAttribute("location", location.getCity());
		request.getRequestDispatcher("WEB-INF/store.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
