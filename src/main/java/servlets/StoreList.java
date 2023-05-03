package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DataStores;
import entities.Users;

/**
 * Servlet implementation class Stores
 */
@WebServlet("/StoreList")
public class StoreList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataStores dataStore = new DataStores();
	ArrayList<entities.Stores> stores = new ArrayList<>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreList() {
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
		
		if (user == null || user.getRol().getType().equalsIgnoreCase("Vendedor"))
		{
			sesion.invalidate();
			response.sendRedirect("/control_stock/404.html");
			return;
		}

		request.setAttribute("nombreUsuario", user.getName());
		
		
		try {
			stores = dataStore.readAll();
			request.setAttribute("stores", stores);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response.sendRedirect("/control_stock/500.html");
			return;
		}
		request.getRequestDispatcher("WEB-INF/stores.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
