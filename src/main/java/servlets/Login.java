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

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsersLogic userLogic = new UsersLogic();
	Users user = new Users();   
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
		request.getRequestDispatcher("login.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("Ingresar")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			
			user = userLogic.Validar(email, password);
			
			String rol = user.getRol().getType();
			
			if(user!=null) {
				HttpSession sesion = request.getSession();
				sesion.setAttribute("user", user);
				sesion.setAttribute("pass", password);
				sesion.setAttribute("rol", rol);
				
				request.setAttribute("user", user);
				request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
			}
			else {
				response.sendRedirect("/control_stock/404.html");
				return;
				}
		}
	}

}
