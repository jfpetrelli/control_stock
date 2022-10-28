package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Roles;
import logic.RolesLogic;

/**
 * Servlet implementation class NewRol
 */
@WebServlet("/NewRol")
public class NewRol extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RolesLogic rolLogic = new RolesLogic();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewRol() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/newRol.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Roles rol = new Roles();
		rol.setType(request.getParameter("type"));
		
		//Lo creo en la DB
		rolLogic.create(rol);

		Rol rolServlet = new Rol();
		rolServlet.doGet(request, response);
	}

}
