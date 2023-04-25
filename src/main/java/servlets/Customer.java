package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Customers;
import entities.Users;
import logic.CustomersLogic;

/**
 * Servlet implementation class Customer
 */
@WebServlet("/Customer")
public class Customer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomersLogic customerLogic = new CustomersLogic();
	ArrayList<Customers> customers = new ArrayList<>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Customer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println("Hola Servlet..");
		HttpSession sesion = request.getSession();
		
		Users user = (Users) sesion.getAttribute("usuario");
		
		if (user == null)
		{
			response.sendRedirect("/control_stock/500.html");
			return;
		}
		else
			if(user.getRol().getType().equalsIgnoreCase("Vendedor"))
				{
					response.sendRedirect("/control_stock/404.html");
					return;
				}
		
		request.setAttribute("usuario", user);

		customers = customerLogic.getAll();
		request.setAttribute("customers", customers);
		request.getRequestDispatcher("WEB-INF/customerList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}