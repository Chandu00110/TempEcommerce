<%@page import="shoppingCart.Models.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shoppingCart.Models.Product"%>
<%@page import="shoppingCart.Connection.DbCon"%>
<%@page import="shoppingCart.Dao.ProductDao"%>
<%@page import="java.util.List"%>
<%@page import="shoppingCart.Models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}
ProductDao pdao = new ProductDao(DbCon.getConnection());
List<Product> product = pdao.getAllProducts();

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if(cart_list != null){
	request.setAttribute("cart_list", cart_list);
}
%>
<title>Shopping Cart</title>
<%@ include file="includes/header.jsp"%>
</head>
<body>
	<%@ include file="includes/navbar.jsp"%>
	<div class="container">
		<div class="card-header my-3">All Products</div>
		<div class="row">
			<%
			if (!product.isEmpty()) {
				for (Product p : product) {
				%>
				<div class="col-md-3 my-3">
					<div class="card w-100" style="width: 18rem;">
						<img src="product-images/<%=p.getImage()%>" class="card-img-top"
							alt="...." width="10px;">
						<div class="card-body">
							<h5 class="card-title"><%=p.getName()%></h5>
							<h6 class="card-title">
								Price: $<%=p.getPrice()%></h6>
							<h6 class="card-title">
								Category:<%=p.getCategory()%></h6>
							<div class="mt-3 d-flex justify-content-between">
								<a href="add-to-cart?id=<%= p.getId() %>" class="btn btn-dark">Add to Cart</a>
								<a href="" class="btn btn-primary">Buy Now</a>
							</div>
						</div>
					</div>
				</div>
				<%
				}
			}
			%>
		</div>
	</div>

	<%@ include file="includes/footer.jsp"%>
</body>
</html>