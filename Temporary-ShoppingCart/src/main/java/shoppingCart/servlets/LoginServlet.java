package shoppingCart.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingCart.Connection.DbCon;
import shoppingCart.Dao.UserDao;
import shoppingCart.Models.User;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("login.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try{
			String email = request.getParameter("login-email");
			String password = request.getParameter("login-pwd");
			
			UserDao udao = new UserDao(DbCon.getConnection());
			User user = udao.userLogin(email, password);
			if(user != null) {
				out.print("user login");
				request.getSession().setAttribute("auth", user);
				response.sendRedirect("index.jsp");
			}
			else {
				out.print("login failed");
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
			out.print(ex);
		}
	}

}
