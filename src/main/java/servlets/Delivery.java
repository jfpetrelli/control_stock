package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.ListSales;
import entities.Sales;
import entities.Users;
import logic.SalesLogic;

/**
 * Servlet implementation class Order
 */
@WebServlet("/Delivery")
public class Delivery extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SalesLogic salesLogic = new SalesLogic();
	ArrayList<Sales> sales = new ArrayList<>();
	ArrayList<ListSales> listSales = new ArrayList<>();
    String sale_id ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delivery() {
        super();
        // TODO Auto-generated constructor stub
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
				sesion.invalidate();
				response.sendRedirect("/control_stock/404.html");
				return;
			}

			request.setAttribute("tipoRol", user.getRol().getType());
			request.setAttribute("nombreUsuario", user.getName());
			sales = salesLogic.getSalesWithoutStatus();
		
		if(sales == null) {
			
			response.sendRedirect("/control_stock/500.html");
			return;
		} 
		
		String action = request.getParameter("action");
		
		
		request.setAttribute("sales", sales);
		request.setAttribute("msg", "");
		if(action != null) 
			{
				 
				if(action.equals("Traer Venta")) 
					{
						sale_id = request.getParameter("sale");	
						if(sale_id.equals(""))
						{
							request.setAttribute("msg", "Seleccione una venta");
							request.getRequestDispatcher("WEB-INF/delivery.jsp").forward(request, response);
							return;
						}
						listSales = salesLogic.getSalesWithoutStatus(Integer.parseInt(sale_id));
						
						request.setAttribute("sale", sale_id);
						request.setAttribute("listSales", listSales);
	
					}
				if(action.equals("Terminar Pedido")) 
				{
					if(request.getParameterValues("status") != null) {
						String[] status = request.getParameterValues("status");
						
						 for(int i=0;i<status.length;i++)
					       {
								
								for(ListSales listSale: listSales) {
									
									if(listSale.getPos() == Integer.parseInt(status[i])) {
										listSale.setStatus(true);
										
									}
									
								}
								
					       }
						 
						 String msgAddOK = salesLogic.updateStatus(listSales);
						request.setAttribute("msgAddOK", msgAddOK);


					}else {
						request.setAttribute("msgpedido", "Seleccione al menos una entrega");
						request.getRequestDispatcher("WEB-INF/delivery.jsp").forward(request, response);
						return;
					}
					

				}
				
					
		       
			}
		
		request.getRequestDispatcher("WEB-INF/delivery.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
