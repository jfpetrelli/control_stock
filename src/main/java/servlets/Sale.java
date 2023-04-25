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
import javax.servlet.http.HttpSession;

import entities.Customers;
import entities.Products;
import entities.Sales;
import entities.Stores;
import logic.CustomersLogic;
import logic.ProductsLogic;
import logic.SalesLogic;
import logic.StoresLogic;
import entities.Users;

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
    Double total = 0.0;
	
	
	

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

		HttpSession sesion = request.getSession();
		Users user = (Users) sesion.getAttribute("usuario");
		
		if (user == null)
			{
				response.sendRedirect("/control_stock/500.html");
				return;
			}

			request.setAttribute("tipoRol", user.getRol().getType());
			request.setAttribute("nombreUsuario", user.getName());
		
		
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

		if(action != null) 
			{

				String datetime = request.getParameter("datetime");
				 
				if(action.equals("Buscar Articulos")) 
					{
						store_id = request.getParameter("store");
						customer_id = request.getParameter("customer");
						
						if(store_id.equals("") || customer_id.equals(""))
							{
								request.setAttribute("msg", "Complete todos los datos");
								request.getRequestDispatcher("WEB-INF/sale.jsp").forward(request, response);
								return;
					     	}
		
						total = 0.0;
					    products = productsLogic.getProductsStores(Integer.parseInt(store_id));
				        request.setAttribute("products", products);
				        request.setAttribute("customer", customer_id);
				        request.setAttribute("store", store_id);
				        request.setAttribute("datetime", datetime);
		
				        sale = new Sales();
	
					}
				if(action.equals("Agregar")) 
					{
					
						if(products.isEmpty()) 
							{
								request.setAttribute("msgAddOK", "Seleccione un Articulo");
					        	request.getRequestDispatcher("WEB-INF/sale.jsp").forward(request, response);
								return;
							}
						
				        request.setAttribute("products", products);
				        request.setAttribute("customer", customer_id);
				        request.setAttribute("store", store_id);
				        request.setAttribute("datetime", datetime);
				        request.setAttribute("products_selected", sale.getProducts());
				        String msgAddOK;
				        
				        if(request.getParameter("product").equals("")) 
				        	{
				        		msgAddOK = "Seleccione un Articulo";
				        	
				        	}
				        else 
				        	{
				        		msgAddOK = SalesLogic.quantityAddOK(products, request.getParameter("quantity"), request.getParameter("product"));
				        	}
				        
				        if(msgAddOK != null) 
				        	{
					        	request.setAttribute("msgAddOK", msgAddOK);
					        	request.getRequestDispatcher("WEB-INF/sale.jsp").forward(request, response);
								return;
				        	}
		
				        Products product_selected = salesLogic.setProduct(products, request.getParameter("quantity"), request.getParameter("product"));
				        
				        total += product_selected.getPrice() * product_selected.getStock();
				        sale.addProduct(product_selected);
				        request.setAttribute("products_selected", sale.getProducts());
				        request.setAttribute("total", total);
				        
				        System.out.println(product_selected.getPos());
			        
					}
				
				if(action.equals("Realizar Venta")) 
					{
					
						if(sale == null)
							{
					        	request.getRequestDispatcher("WEB-INF/sale.jsp").forward(request, response);
								return;
							}
						Customers customer = new Customers();
						customer.setId(Integer.parseInt(customer_id));
						Stores store = new Stores();
						store.setId(Integer.parseInt(store_id));
						sale.setCustomer(customer);
						sale.setStore(store);
						sale.setDatetime(this.datetime);
						
						String msgAddOK = salesLogic.addSale(sale);
						request.setAttribute("msgAddOK", msgAddOK);
						sale = null;
					}
				
				if(action.equals("Eliminar")) 
					{
						int pos = Integer.parseInt(request.getParameter("deleteItem"));
						salesLogic.deleteItem(pos, sale, products);
						 request.setAttribute("products", products);
					     request.setAttribute("customer", customer_id);
					     request.setAttribute("store", store_id);
					     request.setAttribute("datetime", datetime);
					     request.setAttribute("products_selected", sale.getProducts());
					     request.setAttribute("total", sale.getTotal());
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
