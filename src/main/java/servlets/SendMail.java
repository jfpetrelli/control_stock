package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Email;
import entities.Products;
import logic.ProductsLogic;

/**
 * Servlet implementation class SendMail
 */
@WebServlet("/SendMail")
public class SendMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Products> products = new ArrayList<>();
	ProductsLogic productLogic = new ProductsLogic();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String contact = request.getParameter("email");
		String subject = "Lista de productos";	
		String content = null;
		String items = "";
		boolean sended = false;
		products = productLogic.getAll();
		request.setAttribute("products", products);
		
		 for (Products product : products) {
			 String item = null;			 			 
			 item = "  <tr>\n" 
					+ "    <td> " + product.getDetail() + "</td>\n"
					+ "    <td>"+ product.getPrice() +"</td>\n"
					+ "  </tr>\n"; 
			 items = items + item;
		 }
		
		 content = "<!DOCTYPE html>\n"
	                + "<html>\n"
	                + "<head>\n"
	                + "<style>\n"
	                + "table {\n"
	                + "  border-collapse: collapse;\n"
	                + "  width: 100%;\n"
	                + "}\n"
	                + "\n"
	                + "th, td {\n"
	                + "  text-align: left;\n"
	                + "  padding: 8px;\n"
	                + "}\n"
	                + "\n"
	                + "tr:nth-child(even){background-color: #f2f2f2}\n"
	                + "\n"
	                + "th {\n"
	                + "  background-color: #4CAF50;\n"
	                + "  color: white;\n"
	                + "}\n"
	                + "</style>\n"
	                + "</head>\n"
	                + "<body>\n"
	                + "\n"
	                + "<h2>Listado de productos</h2>\n"
	                + "\n"
	                + "<table>\n"
	                + "  <tr>\n"
	                + "    <th>Producto</th>\n"
	                + "    <th>Precio</th>\n"
	                + "  </tr>\n"
	                + items
	                + "</table>\n"
	                + "\n"
	                + "</body>\n"
	                + "</html>";
		
		try {
			Email mail = new Email(contact,subject,content);
			mail.createEmail();
			sended = mail.sendEmail();
		} catch (Exception e) {
			request.setAttribute("error", "No se pudo enviar el mail. Intente nuevamente.");
			request.getRequestDispatcher("WEB-INF/productList.jsp").forward(request, response);
		}

		if(sended) {
			request.setAttribute("error", "Mail enviado correctamente.");
			
		} else {
			request.setAttribute("error", "No se pudo enviar el mail. Intente nuevamente.");
		}
				
		request.getRequestDispatcher("WEB-INF/productList.jsp").forward(request, response);
	}

}
