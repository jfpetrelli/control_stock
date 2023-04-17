package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Products;
import logic.ProductsLogic;

/**
 * Servlet implementation class RemoveProduct
 */
@WebServlet("/RemoveProduct")
public class RemoveProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductsLogic productLogic = new ProductsLogic();
	ArrayList<Products> products = new ArrayList<>();  
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer product_id = Integer.parseInt(request.getParameter("product_id"));
		Products product = productLogic.getById(product_id);
		if(!productLogic.remove(product)) {
		    request.setAttribute("error", "No se pudo borrar el producto.");			
		}
		
		products = productLogic.getAll();
	    request.setAttribute("products", products);
	     
		request.getRequestDispatcher("WEB-INF/productList.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
