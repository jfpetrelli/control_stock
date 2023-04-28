package servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import entities.Users;
import logic.UsersLogic;
import entities.Roles;
import logic.RolesLogic;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsersLogic userLogic = new UsersLogic();
	Users user = new Users();
	RolesLogic rolLogic = new RolesLogic();
	Roles rol = new Roles();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion = request.getSession();
		user = (Users) sesion.getAttribute("usuario");
		
		if (user == null)
		{
			sesion.invalidate();
			response.sendRedirect("/control_stock/login.html");
			return;
		}
		else
			request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		if(action.equals("Desconectarse")) 
			{
				request.getSession().invalidate();
				Login userServlet = new Login();
				userServlet.doGet(request, response);
			}
		
		if(action.equalsIgnoreCase("Ingresar")) 
			{
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				
				
				user = userLogic.Validar(email, password);
			
				
				if(user!=null) 
					{
						HttpSession sesion = request.getSession();
						sesion.setAttribute("usuario", user);
						sesion.setAttribute("nombreUsario", user.getName());
						sesion.setAttribute("tipoRol", user.getRol().getType());
						
						request.setAttribute("nombreUsuario", user.getName());
						request.setAttribute("tipoRol", user.getRol().getType());
						request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
					}
				else 
					{	
						response.sendRedirect("/control_stock/404.html");
						return;
					}
			}
	}

}
