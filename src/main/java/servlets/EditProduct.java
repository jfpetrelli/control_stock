package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Customers;
import entities.Products;
import entities.Users;
import logic.ProductsLogic;



/**
 * Servlet implementation class EditProduct
 */
@WebServlet("/EditProduct")
public class EditProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductsLogic productLogic = new ProductsLogic();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProduct() {
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
		
		//Logica para editar producto
		try {
		Integer product_id = Integer.parseInt(request.getParameter("product_id"));
		Products product = productLogic.getById(product_id);
	    request.setAttribute("product", product);
	    if(product == null) {
			response.sendRedirect("/control_stock/500.html");				
			return;
	    }
		} catch (Exception e) {
			response.sendRedirect("/control_stock/500.html");				
			return;
		}
	     
		request.getRequestDispatcher("WEB-INF/editProduct.jsp").forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer product_id = Integer.parseInt(request.getParameter("product_id"));
		String detail = request.getParameter("detail");
		Double price = Double.parseDouble(request.getParameter("price"));
		
		Products product = new Products();
		product.setId(product_id);
		product.setDetail(detail);
		product.setPrice(price);
		
		//Actualizo en la DB
		productLogic.update(product);


		Product productServlet = new Product();
		productServlet.doGet(request, response);
	}

}
