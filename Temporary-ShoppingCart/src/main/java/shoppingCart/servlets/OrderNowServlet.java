package shoppingCart.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingCart.Connection.DbCon;
import shoppingCart.Dao.OrderDao;
import shoppingCart.Models.Cart;
import shoppingCart.Models.Order;
import shoppingCart.Models.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		try {
			
			User auth = (User) request.getSession().getAttribute("auth");
			if(auth != null) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
				Date date = new Date(); 
				int productId = Integer.parseInt(request.getParameter("id"));
				
				if(productId > 0) {
					int productQuantity = Integer.parseInt(request.getParameter("quantity"));
					if(productQuantity <= 0) {
						productQuantity = 1;
					}
					Order orderModel = new Order();
					orderModel.setId(productId);
					orderModel.setUserId(auth.getId());
					orderModel.setQuantity(productQuantity);
					orderModel.setDate(format.format(date));
					
					OrderDao orderDao = new OrderDao(DbCon.getConnection());
					boolean result = orderDao.insertOrders(orderModel);
					if(result) {
						ArrayList<Cart> cartList = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
						if(cartList != null) {
							for(Cart c : cartList) {
								if(c.getId() == productId) {
									cartList.remove(cartList.indexOf(c));
									break;
								}
							}
						}
						response.sendRedirect("orders.jsp");
					}else {
						System.out.println("order failed");
					}
					
				}
				else {
					ArrayList<Cart> cartList = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
					if(cartList != null) {
						for(Cart c : cartList) {
							Order orderModel = new Order();
							orderModel.setId(c.getId());
							orderModel.setUserId(auth.getId());
							orderModel.setQuantity(c.getQuantity());
							orderModel.setDate(format.format(date));
							
							OrderDao orderDao = new OrderDao(DbCon.getConnection());
							boolean result = orderDao.insertOrders(orderModel);
							if(!result) break;
						}
						cartList.clear();
						response.sendRedirect("orders.jsp");
					}
					else {
						response.sendRedirect("index.jsp");
					}
				}
			}
			else {
				response.sendRedirect("login.jsp");
			}
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
