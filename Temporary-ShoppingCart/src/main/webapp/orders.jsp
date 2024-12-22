<%@page import="shoppingCart.Models.Cart"%>
<%@page import="shoppingCart.Connection.DbCon"%>
<%@page import="shoppingCart.Dao.OrderDao"%>
<%@page import="shoppingCart.Models.Order"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="shoppingCart.Models.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
User auth = (User) request.getSession().getAttribute("auth");
List<Order> order = null;
ArrayList<Cart> cartList = null;
if (auth != null) {
	request.setAttribute("auth", auth);
	order = new OrderDao(DbCon.getConnection()).getUserOrders(auth.getId());
	cartList = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
} else {
	response.sendRedirect("login.jsp");
}
%>
<title>Orders</title>
<%@ include file="includes/header.jsp"%>
</head>
<body>
	<%@ include file="includes/navbar.jsp"%>
	<div class="container">
		<div class="card-header my-3">All Orders</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (order != null) {
					for (Order o : order) {
				%>
				<tr>
					<td><%=o.getDate()%></td>
					<td><%=o.getName()%></td>
					<td><%=o.getCategory()%></td>
					<td><%=o.getQuantity()%></td>
					<td><%=o.getPrice()%></td>
					<td><a class="btn btn-danger btn-sm" href="cancel-order?id=<%= o.getOrderId() %>">Cancel</a></td>
				</tr>
				<%
				}
				}
				else{
					%>
					<p>no orders</p>
					<%
				}
				%>
			</tbody>
		</table>
	</div>

	<%@ include file="includes/footer.jsp"%>
</body>
</html>