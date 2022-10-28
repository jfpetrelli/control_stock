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
 * Servlet implementation class EditRol
 */
@WebServlet("/EditRol")
public class EditRol extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RolesLogic rolLogic = new RolesLogic();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditRol() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer rol_id = Integer.parseInt(request.getParameter("rol_id"));
		Roles rol = rolLogic.getById(rol_id);
	    request.setAttribute("rol", rol);
	     
		request.getRequestDispatcher("WEB-INF/editRol.jsp").forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer rol_id = Integer.parseInt(request.getParameter("rol_id"));
		String type = request.getParameter("type");
		
		Roles rol = new Roles();
		rol.setId(rol_id);
		rol.setType(type);
		
		//Actualizo en la DB
		rolLogic.update(rol);


		Rol rolServlet = new Rol();
		rolServlet.doGet(request, response);
	}


}
