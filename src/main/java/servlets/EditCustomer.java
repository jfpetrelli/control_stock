package servlets;

import java.io.IOException;

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
 * Servlet implementation class EditCustomer
 */
@WebServlet("/EditCustomer")
public class EditCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomersLogic customerLogic = new CustomersLogic();

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			HttpSession sesion = request.getSession();
			Users user = (Users) sesion.getAttribute("usuario");
			
			//Chequeo si el usuario es Vendedor o no hay usuario
			if (user == null || user.getRol().getType().equalsIgnoreCase("Vendedor")) {
					sesion.invalidate();
					response.sendRedirect("/control_stock/404.html");
					return;
			}

			request.setAttribute("nombreUsuario", user.getName());
			
			//Logica para editar usuario
			Integer customer_id = Integer.parseInt(request.getParameter("customer_id"));
			Customers customer = customerLogic.getById(customer_id);
			
			if (customer == null)
			{
				response.sendRedirect("/control_stock/404.html");
				return;
			}
			
		    request.setAttribute("customer", customer);			
		} catch (Exception e) {
			response.sendRedirect("/control_stock/404.html");
			return;
		}
			     
		request.getRequestDispatcher("WEB-INF/editCustomer.jsp").forward(request, response); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Integer customer_id = Integer.parseInt(request.getParameter("customer_id"));
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String comercialName = request.getParameter("comercialName");
		String mail = request.getParameter("mail");
		
		Customers customer = new Customers();
		customer.setId(customer_id);
		customer.setName(name);
		customer.setSurnarme(surname);
		customer.setComercial_name(comercialName);
		customer.setMail(mail);
		customerLogic.update(customer);

		Customer customerServlet = new Customer();
		customerServlet.doGet(request, response);
		
	}

}
