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
import logic.SalesLogic;
import logic.StoresLogic;

/**
 * Servlet implementation class Sale
 */
@WebServlet("/Sale")
public class Sale extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	CustomersLogic customersLogic = new CustomersLogic();
	ArrayList<Customers> customers = new ArrayList<>();
	StoresLogic storesLogic = new StoresLogic();
	ArrayList<Stores> stores = new ArrayList<>();
	ProductsLogic productsLogic = new ProductsLogic();
	ArrayList<Products> products = new ArrayList<>();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
	LocalDateTime datetime = LocalDateTime.now();
	SalesLogic salesLogic = new SalesLogic();
	Sales sale;
	 String store_id;
     String customer_id ;

	
	
	

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sale() {
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
		
		if(customers == null || stores == null) {
			
			response.sendRedirect("/control_stock/500.html");
			return;
		} 
		
		String action = request.getParameter("action");
		
	 
		request.setAttribute("customers", customers);
		request.setAttribute("stores", stores);
		request.setAttribute("msg", "");
		String formattedString = datetime.format(formatter);
		request.setAttribute("datetime", formattedString);

		if(action != null) {

			 String datetime = request.getParameter("datetime");
			 
			if(action.equals("Buscar Articulos")) {
				
				 store_id = request.getParameter("store");
				 customer_id = request.getParameter("customer");


			     if(store_id.equals("") || customer_id.equals("")) {
			    	 	
						request.setAttribute("msg", "Complete todos los datos");
						request.getRequestDispatcher("WEB-INF/sale.jsp").forward(request, response);
						return;
			     }

			    products = productsLogic.getProductsStores(Integer.parseInt(store_id));
		        request.setAttribute("products", products);
		        request.setAttribute("customer", customer_id);
		        request.setAttribute("store", store_id);
		        request.setAttribute("datetime", datetime);

		        sale = new Sales();

			}
			if(action.equals("Agregar")) {
				
		        request.setAttribute("products", products);
		        request.setAttribute("customer", customer_id);
		        request.setAttribute("store", store_id);
		        request.setAttribute("datetime", datetime);
		        request.setAttribute("products_selected", sale.getProducts());
		        String msgAddOK;
		        
		        if(request.getParameter("product").equals("")) {
		        	msgAddOK = "Seleccione un Articulo";
		        	
		        }else {

		        	msgAddOK = SalesLogic.quantityAddOK(products, request.getParameter("quantity"), request.getParameter("product"));
		        }
		        
		        if(msgAddOK != null) {
		        	request.setAttribute("msgAddOK", msgAddOK);
		        	request.getRequestDispatcher("WEB-INF/sale.jsp").forward(request, response);
					return;
		        }

		        Products product_selected = SalesLogic.setProduct(products, request.getParameter("quantity"), request.getParameter("product"));

		        sale.addProduct(product_selected);
		        request.setAttribute("products_selected", sale.getProducts());
		        
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
