package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DataLocations;
import entities.Location;
import entities.Stores;
import entities.Users;
import logic.StoresLogic;

/**
 * Servlet implementation class UpdateStore
 */
@WebServlet("/UpdateStore")
public class UpdateStore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StoresLogic storeLogic = new StoresLogic();
	DataLocations dataLocation = new DataLocations();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStore() {
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
		
		
		//Logica para editar deposito
		try {
			ArrayList<Location> locations = dataLocation.readAll();
			Integer store_id = Integer.parseInt(request.getParameter("store"));
			entities.Stores store = storeLogic.getById(store_id);
			request.setAttribute("store", store);
		    request.setAttribute("locations", locations);
		    request.setAttribute("location_store", store.getLocation_id());
		} catch (Exception e) {
			response.sendRedirect("/control_stock/500.html");
			return;			
		}

		request.getRequestDispatcher("WEB-INF/editStore.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Stores store = new Stores();
		Integer id = Integer.parseInt(request.getParameter("store"));
		String detail = request.getParameter("detail");
		String address = request.getParameter("address");
		Integer locationId = Integer.parseInt(request.getParameter("location"));
		store.setLocation_id(locationId);
		store.setDetail(detail);
		store.setAddress(address);
		store.setId(id);
		storeLogic.update(store);
		StoreList storeServlet = new StoreList();
		storeServlet.doGet(request, response);
	}

}
