package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.LocationsLogic;

/**
 * Servlet implementation class CreateLocation
 */
@WebServlet("/CreateLocation")
public class CreateLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LocationsLogic locationLogic = new LocationsLogic();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateLocation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/createLocation.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		entities.Location location = new entities.Location();

		location.setCity(request.getParameter("city"));
		location.setState(request.getParameter("state"));
		location.setZipcode(request.getParameter("cp"));
		locationLogic.create(location);
		
		LocationList locations = new LocationList();		
		locations.doGet(request, response);
	}

}
