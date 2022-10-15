package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DataStores;
import logic.StoresLogic;

/**
 * Servlet implementation class CreateStore
 */
@WebServlet("/CreateStore")
public class CreateStore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StoresLogic storeLogic = new StoresLogic();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateStore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/createStore.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		entities.Stores store = new entities.Stores();
		store.setAddress(request.getParameter("address"));
		store.setDetail(request.getParameter("detail"));
		storeLogic.create(store);
		
		Stores stores = new Stores();		
		stores.doGet(request, response);
	}

}
