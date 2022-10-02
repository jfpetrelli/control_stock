package servlets;

import java.io.IOException;
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

/**
 * Servlet implementation class NewStock
 */
@WebServlet("/NewStock")
public class NewStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductsLogic productLogic = new ProductsLogic();
	DataProducts dataProduct = new DataProducts();
	DataStores dataStore = new DataStores();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewStock() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/createStock.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer productId = Integer.parseInt(request.getParameter("product"));
		Integer storeId = Integer.parseInt(request.getParameter("store"));
		Integer quantity = Integer.parseInt(request.getParameter("quantity"));
		
		Products product = dataProduct.getById(productId);
		Stores store = dataStore.getById(storeId);
		productLogic.createStock(store, product, quantity);
		doGet(request, response);
	}

}
