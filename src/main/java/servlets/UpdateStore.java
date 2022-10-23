package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Stores;
import logic.StoresLogic;

/**
 * Servlet implementation class UpdateStore
 */
@WebServlet("/UpdateStore")
public class UpdateStore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StoresLogic storeLogic = new StoresLogic();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("store") == null) {
			response.sendRedirect("/control_stock/500.html");
			return;
		}
		Integer store_id = Integer.parseInt(request.getParameter("store"));
		entities.Stores store = storeLogic.getById(store_id);
		request.setAttribute("store", store);
		request.getRequestDispatcher("WEB-INF/editStore.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Stores store = new Stores();
		Integer id = Integer.parseInt(request.getParameter("store"));
		String detail = request.getParameter("detail");
		String address = request.getParameter("address");
		store.setDetail(detail);
		store.setAddress(address);
		store.setId(id);
		storeLogic.update(store);
		StoreList storeServlet = new StoreList();
		storeServlet.doGet(request, response);
	}

}
