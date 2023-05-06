package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Roles;
import entities.Users;
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
		
		//Logica para editar Rol
		try {
			Integer rol_id = Integer.parseInt(request.getParameter("rol_id"));
			Roles rol = rolLogic.getById(rol_id);
		    request.setAttribute("rol", rol);
		    if(rol == null) {
				response.sendRedirect("/control_stock/500.html");				
				return;
		    }
		} catch (Exception e) {
			response.sendRedirect("/control_stock/500.html");				
			return;
		}
	     
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
