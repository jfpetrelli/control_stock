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
 * Servlet implementation class NewCustomer
 */
@WebServlet("/NewCustomer")
public class NewCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomersLogic customerLogic = new CustomersLogic();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/newCustomer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String comercialName = request.getParameter("comercialName");
		String mail = request.getParameter("email");
		
		Customers customer = new Customers();
		customer.setName(name);
		customer.setSurnarme(surname);
		customer.setComercial_name(comercialName);
		customer.setMail(mail);
		customerLogic.create(customer);

		Customer customerServlet = new Customer();
		customerServlet.doGet(request, response);
	}

}
