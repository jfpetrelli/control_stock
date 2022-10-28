package servlets;

import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Products;
import logic.ProductsLogic;

/**
 * Servlet implementation class Product
 */
@WebServlet({ "/Product", "/Products", "/product", "/products" })
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Products> products = new ArrayList<>();
	ProductsLogic productLogic = new ProductsLogic();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Product() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
