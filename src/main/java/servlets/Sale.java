package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Customers;
import entities.Products;
import entities.Sales;
import entities.Stores;
import logic.CustomersLogic;
import logic.ProductsLogic;
import logic.StoresLogic;

/**
 * Servlet implementation class Sale
 */
@WebServlet("/Sale")
public class Sale extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sale() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getParameter("action");
		double total = 0.0;
			CustomersLogic customersLogic = new CustomersLogic();
			ArrayList<Customers> customers = new ArrayList<>();
    	
			customers = customersLogic.getAll();
        
			StoresLogic storesLogic = new StoresLogic();
			ArrayList<Stores> stores = new ArrayList<>();
        
			stores = storesLogic.getAll();
        

			request.setAttribute("customers", customers);
			request.setAttribute("stores", stores);
		
			
			ProductsLogic productsLogic = new ProductsLogic();
	        ArrayList<Products> products = new ArrayList<>();

		if(action != null) {
			
			 String store_id = request.getParameter("store");
		     String customer_id = request.getParameter("customer");
		     products = productsLogic.getProductsStores(Integer.parseInt(store_id));
		     String datetime = request.getParameter("datetime");
		     LocalDateTime local_datetime =  LocalDateTime.parse(datetime,DateTimeFormatter.ISO_DATE_TIME);

		     System.out.println(local_datetime);
			if(action.equals("Buscar Articulos")) {

		        request.setAttribute("products", products);
		        request.setAttribute("customer", customer_id);
		        request.setAttribute("store", store_id);
		        request.setAttribute("datetime", datetime);

			}
			if(action.equals("Agregar")) {
		        request.setAttribute("products", products);
		        request.setAttribute("customer", customer_id);
		        request.setAttribute("store", store_id);
		        request.setAttribute("datetime", datetime);
		        
		        int product_id = Integer.parseInt(request.getParameter("product"));
		        int quantity = Integer.parseInt(request.getParameter("quantity"));


		        
				}		        
		       
		}
		
		request.getRequestDispatcher("WEB-INF/sale.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
