package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Customers;
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
			
		Integer customer_id = Integer.parseInt(request.getParameter("customer_id"));
		Customers customer = customerLogic.getById(customer_id);
	    request.setAttribute("customer", customer);
	     
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
