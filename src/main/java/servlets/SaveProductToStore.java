package servlets;

import java.io.IOException;
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

/**
 * Servlet implementation class SaveProductToStore
 */
@WebServlet("/SaveProductToStore")
public class SaveProductToStore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductsLogic productLogic = new ProductsLogic();
	DataProducts dataProduct = new DataProducts();
	DataStores dataStore = new DataStores();
	ArrayList<Products> products = new ArrayList<>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveProductToStore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String store_id = request.getParameter("store");
		Integer productId = Integer.parseInt(request.getParameter("product"));
		Integer storeId = Integer.parseInt(request.getParameter("store"));
		Integer quantity = Integer.parseInt(request.getParameter("quantity"));
		
		Products product = dataProduct.getById(productId);
		Stores store = dataStore.getById(storeId);
		
		dataProduct.createStock(store, product, quantity);
		
		products = productLogic.getProductsStores(storeId);
	    request.setAttribute("store", store_id);
	    request.setAttribute("products", products);
//		request.getRequestDispatcher("WEB-INF/addProductToStore.jsp").forward(request, response);
		
		AddProductToStore apts = new AddProductToStore();
		apts.doGet(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
