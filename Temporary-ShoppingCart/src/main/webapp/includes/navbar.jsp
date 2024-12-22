
<div class="container">
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="index.jsp">E-Commerce Shopping Cart</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="index.jsp">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="cart.jsp">Cart
								<span class="badge bg-danger">${cart_list.size()}</span>
						</a></li>
					<% if(auth != null){
						%>
					<li class="nav-item"><a class="nav-link" href="orders.jsp"
						tabindex="-1" aria-disabled="true">Order</a></li>
					<li class="nav-item"><a class="nav-link" href="log-out"
						tabindex="-1" aria-disabled="true">Logout</a></li>
					<%}
					else{
					%>
					<li class="nav-item"><a class="nav-link" href="login.jsp"
						tabindex="-1" aria-disabled="true">LoginIn</a></li>
					<%} %>
				</ul>
			</div>
		</div>
	</nav>

</div>
