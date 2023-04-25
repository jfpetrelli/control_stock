package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Customers;
import logic.CustomersLogic;

/**
 * Servlet implementation class RemoveCustomer
 */
@WebServlet("/RemoveCustomer")
public class RemoveCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomersLogic customerLogic = new CustomersLogic();
	ArrayList<Customers> customers = new ArrayList<>();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveCustomer() {
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
		if(!customerLogic.remove(customer)) {
		    request.setAttribute("error", "No se pudo borrar el cliente.");			
		}
		
		customers = customerLogic.getAll();
	    request.setAttribute("customers", customers);
	     
		request.getRequestDispatcher("WEB-INF/customerList.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
