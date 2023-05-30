package servlets;

import java.util.ArrayList;
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
 * Servlet implementation class RemoveRol
 */
@WebServlet("/RemoveRol")
public class RemoveRol extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RolesLogic rolLogic = new RolesLogic();
	ArrayList<Roles> roles = new ArrayList<>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveRol() {
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
		
		
		//Logica para remover Rol
		Integer rol_id = Integer.parseInt(request.getParameter("rol_id"));
		Roles rol = rolLogic.getById(rol_id);
		if(!rolLogic.remove(rol)) {
		    request.setAttribute("error", "No se pudo borrar el rol.");
		}
		
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
