package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Customers;
import entities.Stores;
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
