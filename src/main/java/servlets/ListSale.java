package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Customers;
import entities.Sales;
import entities.Stores;
import entities.Users;
import logic.CustomersLogic;
import logic.SalesLogic;
import logic.StoresLogic;

/**
 * Servlet implementation class Sale
 */
@WebServlet("/ListSale")
public class ListSale extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	CustomersLogic customersLogic = new CustomersLogic();
	ArrayList<Customers> customers = new ArrayList<>();
	StoresLogic storesLogic = new StoresLogic();
	ArrayList<Stores> stores = new ArrayList<>();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
	LocalDate d = LocalDate.now();
	LocalDateTime datetime = d.atStartOfDay();
	SalesLogic salesLogic = new SalesLogic();
	Sales sale;
	String store_id;
    String customer_id ;
    Double total = 0.0;
	
	
	

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListSale() {
        super();
        // TODO Auto-generated constructor stub
        customers = customersLogic.getAll();
        stores = storesLogic.getAll();

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
		
		//Logica para ver listado de Ventas
		if(customers == null || stores == null) {
			
			response.sendRedirect("/control_stock/500.html");
			return;
		} 
		
		String action = request.getParameter("action");
		
		
		request.setAttribute("customers", customers);
		request.setAttribute("stores", stores);
		request.setAttribute("msg", "");
		String formattedString = datetime.format(formatter);
		request.setAttribute("desde_datetime", formattedString);
		request.setAttribute("hasta_datetime", formattedString);
		


		if(action != null) {
			
			

			 String desde_datetime = request.getParameter("desde_datetime");
			 String hasta_datetime = request.getParameter("hasta_datetime");
			 
			if(desde_datetime.isEmpty() || hasta_datetime.isEmpty()) {
				
				request.setAttribute("msgAddOK", "Ingrese fecha");
	        	request.getRequestDispatcher("WEB-INF/listSale.jsp").forward(request, response);
				return;
			}
			if(action.equals("Filtrar")) {
				
				 store_id = request.getParameter("store");
				 customer_id = request.getParameter("customer");
				 request.setAttribute("customer", customer_id);
				 request.setAttribute("store", store_id);
				 request.setAttribute("desde_datetime", desde_datetime);
				 request.setAttribute("hasta_datetime", hasta_datetime);
				 
				 request.setAttribute("ls", salesLogic.listSales(customer_id,store_id, LocalDateTime.parse(desde_datetime, formatter),LocalDateTime.parse(hasta_datetime, formatter)));
			}
			
		       
		}
		
		request.getRequestDispatcher("WEB-INF/listSale.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
