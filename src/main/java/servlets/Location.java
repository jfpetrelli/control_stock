package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Stores;
import logic.LocationsLogic;

/**
 * Servlet implementation class Location
 */
@WebServlet("/Location")
public class Location extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LocationsLogic locationLogic = new LocationsLogic();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Location() {
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
			if(location == null) {
				response.sendRedirect("/control_stock/500.html");
				return;
			}
			request.setAttribute("location", location);
		} catch (Exception e) {
			response.sendRedirect("/control_stock/500.html");
			return;
		}

		request.getRequestDispatcher("WEB-INF/location.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
