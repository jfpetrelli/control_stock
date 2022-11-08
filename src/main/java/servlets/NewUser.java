package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.RolesLogic;
import logic.UsersLogic;
import entities.Roles;
import entities.Users;

/**
 * Servlet implementation class NewUser
 */
@WebServlet("/NewUser")
public class NewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RolesLogic rolLogic = new RolesLogic();
	ArrayList<Roles> roles = new ArrayList<>();
	UsersLogic userLogic = new UsersLogic();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUser() {
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
		
		request.getRequestDispatcher("WEB-INF/newUser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Users user = new Users();
		user.setName(request.getParameter("name"));
		user.setLastname(request.getParameter("lastname"));
		user.setUsername(request.getParameter("username"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setRol(rolLogic.getById(Integer.parseInt(request.getParameter("rol_id"))));
		
		//Actualizo en la DB
		userLogic.create(user);

		User userServlet = new User();
		userServlet.doGet(request, response);
	}

}
