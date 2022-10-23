package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.StoresLogic;

/**
 * Servlet implementation class DeleteStore
 */
@WebServlet("/DeleteStore")
public class DeleteStore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StoresLogic storeLogic = new StoresLogic();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteStore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("store") == null || request.getParameter("store").isEmpty()) {
			response.sendRedirect("/control_stock/500.html");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		entities.Stores store = new entities.Stores();
		if(request.getParameter("store") == null || request.getParameter("store").isEmpty()) {
			response.sendRedirect("/control_stock/500.html");
			return;
		}
		store.setId(Integer.parseInt(request.getParameter("store")));
		storeLogic.delete(store);
		StoreList stores = new StoreList();
		stores.doGet(request, response);
	}

}
