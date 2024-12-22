<%@page import="java.text.DecimalFormat"%>
<%@page import="shoppingCart.Connection.DbCon"%>
<%@page import="shoppingCart.Dao.ProductDao"%>
<%@page import="shoppingCart.Models.Cart"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="shoppingCart.Models.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProducts = null;
if (cart_list != null) {
	ProductDao pdoa = new ProductDao(DbCon.getConnection());
	cartProducts = pdoa.getCartProducts(cart_list);
	double total = pdoa.getCartPrice(cart_list);
	request.setAttribute("cart_list", cart_list);
	request.setAttribute("total", total);
}
%>
<title>Cart</title>
<%@ include file="includes/header.jsp"%>
</head>
<body>
	<%@ include file="includes/navbar.jsp"%>

	<div class="container">
		<div class="d-flex py-3">
			<h3>Total Price: ${(total>0)? dcf.format(total) : 0 }</h3>
			<a href="order-now?id=0" class="mx-3 btn btn-primary">CheckOUt</a>
		</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (cart_list != null) {
					for (Cart c : cartProducts) {
				%>
				<tr>
					<td><%=c.getName()%></td>
					<td><%=c.getCategory()%></td>
					<td><%=c.getPrice()%></td>
					<td>
						<form action="order-now" method="post">
							<input type="hidden" name="id" value="<%=c.getId()%>"
								class="form-input">

							<div class="form-group d-flex justify-content-between w-50">
								<a class="btn btn-sm btn-decr"
									href="quantity-inc-dec?action=dec&id=<%=c.getId()%>"> <i
									class="fas fa-minus-square"></i></a> <input type="text"
									name="quantity" class="form-control w-50"
									value="<%=c.getQuantity()%>" readonly> <a
									class="btn btn-sm btn-incr"
									href="quantity-inc-dec?action=inc&id=<%=c.getId()%>"> <i
									class="fas fa-plus-square"></i></a>

								<button type="submit" class="btn btn-primary btn-sm ">Buy</button>
							</div>
						</form>
					</td>
					<td><a href="remove-from-cart?id=<%=c.getId()%>"
						class="btn btn-sm btn-danger">Remove</a></td>
				</tr>
				<%
				}
				}
				%>
			</tbody>
		</table>
	</div>

	<%@ include file="includes/footer.jsp"%>
</body>
</html>