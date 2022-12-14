package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DataProducts;
import data.DataStores;
import entities.Products;
import entities.Stores;
import logic.ProductsLogic;
import logic.StoresLogic;

/**
 * Servlet implementation class Stock
 */
@WebServlet("/Stock")
public class Stock extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductsLogic productLogic = new ProductsLogic();
	StoresLogic storeLogic = new StoresLogic();
	DataProducts dataProduct = new DataProducts();
	DataStores dataStore = new DataStores();
	ArrayList<Stores> stores = new ArrayList<>();
	ArrayList<Products> products = new ArrayList<>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Stock() {
        super();
        stores = storeLogic.getAll();
        products = productLogic.getAll();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		stores = storeLogic.getAll();
		request.setAttribute("stores", stores);
		request.getRequestDispatcher("WEB-INF/stores_stock.jsp").forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Stores store = storeLogic.getById(Integer.parseInt(request.getParameter("store")));		
			Integer store_id = Integer.parseInt(request.getParameter("store"));
			
			request.setAttribute("products", productLogic.getProductsStores(store_id));
			request.setAttribute("store", store);
		} catch (Exception e) {
			response.sendRedirect("/control_stock/500.html");
			return;
		}


		request.getRequestDispatcher("WEB-INF/stock.jsp").forward(request, response);
	}

}
