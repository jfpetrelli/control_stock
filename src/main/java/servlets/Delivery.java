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
	
	SalesLogic salesLogic;
	ArrayList<Sales> sales = new ArrayList<>();
	ArrayList<ListSales> listSales = new ArrayList<>();
    String sale_id;
       
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
		
		//Chequeo si el usuario es Vendedor o no hay usuario
		if (user == null || user.getRol().getType().equalsIgnoreCase("Vendedor"))
			{
				sesion.invalidate();
				response.sendRedirect("/control_stock/404.html");
				return;
			}

		request.setAttribute("nombreUsuario", user.getName());
		

		salesLogic = new SalesLogic();
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
					
						String[] status = request.getParameterValues("status");
						
						if(status == null) {
							request.setAttribute("msgpedido", "Seleccione al menos un artículo entregado.");
							request.getRequestDispatcher("WEB-INF/delivery.jsp").forward(request, response);
							return;

						}else {
							for(int i=0;i<status.length;i++)
								{
								System.out.println(status[i]);
									for(ListSales listSale: listSales) {
									
										if(listSale.getPos() == Integer.parseInt(status[i])) {
											listSale.setStatus(true);
										
										}									
									}
								}
						}
						 
						 String msgAddOK = salesLogic.updateStatus(listSales);
						request.setAttribute("msgAddOK", msgAddOK);
						sales = salesLogic.getSalesWithoutStatus();
						request.setAttribute("sales", sales);
					   
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
