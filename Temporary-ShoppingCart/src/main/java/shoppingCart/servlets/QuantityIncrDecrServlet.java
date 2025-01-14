package shoppingCart.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingCart.Models.Cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/quantity-inc-dec")
public class QuantityIncrDecrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset= UTF-8");
		PrintWriter out = response.getWriter();
		try {
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));
			
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			
			if(action != null && id >=1) {
				if(action.equals("inc")) {
					for(Cart c : cart_list) {
						if(c.getId() == id ) {
							int quantity = c.getQuantity();
							quantity++;
							c.setQuantity(quantity);
							response.sendRedirect("cart.jsp");
						}
					}
				}
				if(action.equals("dec")) {
					for(Cart c : cart_list) {
						if(c.getId() == id && c.getQuantity() > 1) {
							int quantity = c.getQuantity();
							quantity--;
							c.setQuantity(quantity);
							response.sendRedirect("cart.jsp");
						}
					}
				}
			}
			else {
				response.sendRedirect("cart.jsp");
			}
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
