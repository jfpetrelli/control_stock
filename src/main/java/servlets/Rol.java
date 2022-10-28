package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Roles;
import logic.RolesLogic;

/**
 * Servlet implementation class Rol
 */
@WebServlet("/Rol")
public class Rol extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Roles> roles = new ArrayList<>();
	RolesLogic rolLogic = new RolesLogic();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rol() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		roles = rolLogic.getAll();
		request.setAttribute("roles", roles);
		request.getRequestDispatcher("WEB-INF/rolList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
