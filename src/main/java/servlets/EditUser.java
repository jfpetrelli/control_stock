package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Users;
import logic.UsersLogic;
import entities.Roles;
import logic.RolesLogic;

/**
 * Servlet implementation class EditRol
 */
@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsersLogic userLogic = new UsersLogic();
	RolesLogic rolLogic = new RolesLogic();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		ArrayList<Roles> roles = rolLogic.getAll();
		
		Integer user_id = Integer.parseInt(request.getParameter("user_id"));
		Users user = userLogic.getById(user_id);
		Integer user_IdRol = user.getRol().getId();
		
		
	    request.setAttribute("user", user);
	    request.setAttribute("user_IdRol", user_IdRol);
	    request.setAttribute("roles", roles);
	    
		} catch (Exception e) {
			response.sendRedirect("/control_stock/500.html");
			return;			
		}
		
		request.getRequestDispatcher("WEB-INF/editUser.jsp").forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Users user = new Users();
		user.setId(Integer.parseInt(request.getParameter("user_id")));
		user.setName(request.getParameter("name"));
		user.setLastname(request.getParameter("lastname"));
		user.setUsername(request.getParameter("username"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setRol(rolLogic.getById(Integer.parseInt(request.getParameter("rol_id"))));
		
		//Actualizo en la DB
		userLogic.update(user);


		User userServlet = new User();
		userServlet.doGet(request, response);
	}


}