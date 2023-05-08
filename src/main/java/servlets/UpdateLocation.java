package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Customers;
import entities.Stores;
import entities.Users;
import logic.LocationsLogic;

/**
 * Servlet implementation class UpdateLocation
 */
@WebServlet("/UpdateLocation")
public class UpdateLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LocationsLogic locationLogic = new LocationsLogic();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateLocation() {
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
		
		
		//Logica para editar Localidad
		try {
			Integer location_id = Integer.parseInt(request.getParameter("location"));
			entities.Location location = locationLogic.getById(location_id);
		    request.setAttribute("location", location);
		    if(location == null) {
				response.sendRedirect("/control_stock/500.html");	
				return;
		    }
		} catch (Exception e) {
			response.sendRedirect("/control_stock/500.html");	
			return;
		}
	     
		request.getRequestDispatcher("WEB-INF/editLocation.jsp").forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		entities.Location location = new entities.Location();
		Integer id = Integer.parseInt(request.getParameter("location"));
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("cp");

		location.setId(id);
		location.setCity(city);
		location.setState(state);
		location.setZipcode(zipcode);
		locationLogic.update(location);
		LocationList locations = new LocationList();
		locations.doGet(request, response);
	}

}
