package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.LocationsLogic;
import logic.ProductsLogic;

/**
 * Servlet implementation class LocationList
 */
@WebServlet("/LocationList")
public class LocationList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LocationsLogic locationLogic = new LocationsLogic();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocationList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<entities.Location> locations = locationLogic.getAll();
		request.setAttribute("locations", locations);
		request.getRequestDispatcher("WEB-INF/locations.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
