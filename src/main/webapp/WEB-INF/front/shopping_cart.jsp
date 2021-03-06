<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<meta charset="UTF-8">
<title>Shopping Cart</title>
</head>
<body>
	<div class="container mb-4 ">
		<c:if test="${user != null }">
			<header class="py-3">
				<div
					class="row flex-nowrap justify-content-between align-items-center">
					<div
						class="col-4 pt-1 d-flex justify-content-start align-items-center">
						<a class="btn" href="#">Ukr</a> <a class="btn" href="#">Eng</a>
					</div>
					<div
						class="col-8 pt-1 d-flex justify-content-end align-items-center">
						<div class="dropdown">
							<button class="btn dropdown-toggle" type="button"
								id="dropdownMenuButton1" data-bs-toggle="dropdown"
								aria-expanded="false">
								<i class="bi bi-person-circle"></i>
								<c:out value="${user.firstName} ${user.lastName}" />
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
								<li><a class="dropdown-item" href="user-orders">MyOrders</a></li>
								<li><a class="dropdown-item" href="user-logout">Logout</a></li>
							</ul>
						</div>
						<a class="btn p-1" href="view-cart" role="button"><i
							class="bi bi-cart"></i>Shopping cart</a>
					</div>
				</div>
			</header>
		</c:if>
		<c:if test="${user == null }">
			<header class="py-3">
				<div
					class="row flex-nowrap justify-content-between align-items-center">
					<div
						class="col-4 pt-1 d-flex justify-content-start align-items-center">
						<a class="btn" href="#">Ukr</a><a class="btn" href="#">Eng</a>
					</div>
					<div
						class="col-8 pt-1 d-flex justify-content-end align-items-center">
						<a class="btn" href="login">Sign In</a><a class="btn"
							href="view-cart"><i class="bi bi-cart"></i>Shopping Cart</a>
					</div>
				</div>
			</header>
		</c:if>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">

			<div class="container-fluid">
				<a class="navbar-brand" href="home"> <svg
						xmlns="http://www.w3.org/2000/svg" viewBox="0 0 80 27"
						width="80px" height="27px">
						<title>CCC</title><g id="Page-1" stroke="none" stroke-width="1"
							fill="none" fill-rule="evenodd">
						<g id="logo" fill="#E9500E" fill-rule="nonzero">
						<g id="Group">
                     <path
							d="M0.11194,13.51286 L0.11194,13.43915 C0.0481049964,9.86134377 1.44891843,6.41280458 3.98964001,3.89298194 C6.53036158,1.37315929 9.99035305,0.00087657352 13.56751,0.09426 C17.3724373,-0.0954475669 21.0763539,1.35390319 23.74205,4.07558 L20.12928,8.24128 C18.396666,6.44286168 16.0269445,5.39699896 13.53062,5.329 C9.18062,5.329 6.04714,8.94169 6.04714,13.36542 L6.04714,13.43913 C6.04714,17.86286 9.10682,21.54933 13.53062,21.54933 C16.47977,21.54933 18.28615,20.36967 20.31362,18.52647 L23.92636,22.176 C21.2943255,25.2551019 17.3934688,26.9541071 13.3463,26.78411 C9.81264687,26.8414023 6.40739335,25.4603727 3.91187534,22.9578986 C1.41635733,20.4554245 0.0448117789,17.04634 0.11194,13.51286 L0.11194,13.51286 Z"
							id="Path"></path>
						<path
							d="M27.99361,13.51286 L27.99361,13.43915 C27.9297722,9.86134471 29.3305834,6.41280543 31.8713034,3.89298246 C34.4120234,1.3731595 37.8720139,0.000876476198 41.44917,0.09426 C45.2540979,-0.0954514658 48.9580161,1.3538999 51.62371,4.07558 L48.011,8.24128 C46.27837,6.4428491 43.908625,5.3969855 41.41228,5.329 C37.06228,5.329 33.9288,8.94169 33.9288,13.36542 L33.9288,13.43913 C33.9288,17.86286 36.98848,21.54933 41.41228,21.54933 C44.36143,21.54933 46.16781,20.36967 48.19528,18.52647 L51.808,22.176 C49.1759814,25.2550886 45.2751489,26.9540929 41.228,26.78411 C37.6943417,26.8414105 34.2890803,25.4603846 31.7935558,22.9579095 C29.2980312,20.4554345 27.9264815,17.0463453 27.99361,13.51286 Z"
							id="Path"></path>
						<path
							d="M55.8197,13.51286 L55.8197,13.43915 C55.755865,9.86134377 57.1566784,6.41280458 59.6974,3.89298194 C62.2381216,1.37315929 65.6981131,0.00087657352 69.27527,0.09426 C73.0801947,-0.0954498599 76.7841091,1.35390148 79.4498,4.07558 L75.837,8.24128 C74.1043958,6.44287125 71.7346905,5.39700936 69.23838,5.329 C64.88838,5.329 61.75489,8.94169 61.75489,13.36542 L61.75489,13.43913 C61.75489,17.86286 64.81458,21.54933 69.23838,21.54933 C72.18753,21.54933 73.99391,20.36967 76.02138,18.52647 L79.63412,22.176 C77.0020855,25.2551019 73.1012288,26.9541071 69.05406,26.78411 C65.5204069,26.8414023 62.1151534,25.4603727 59.6196353,22.9578986 C57.1241173,20.4554245 55.7525718,17.04634 55.8197,13.51286 L55.8197,13.51286 Z"
							id="Path"></path></g></g></g></svg>
				</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNav"
					aria-controls="navbarNav" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/product-list?category_id=9">????????????</a>
						</li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/product-list?category_id=10">????????????????</a>
						</li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/product-list?category_id=11">????????????</a>
						</li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/product-list?category_id=12">??????????</a>
						</li>
					</ul>
				</div>
			</div>
		</nav>
	</div>
	<div class="container">
		<div class="row"> 
		    <c:choose>
		       <c:when test="${cart != null }">

		         <c:if test="${not cart.isEmpty()}">
		                <div class="col-12"><!-- cart -->
				<table class="table">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Image</th>
							<th scope="col">Item</th>
							<th scope="col">Quantity</th>
							<th scope="col">Price Per Unit</th>
							<th scope="col">Total</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody class="table-striped">
					    <c:forEach var="entry" items="${cart}" varStatus="count">
						<tr>
							<td scope="row">${count.getCount()}</td>
							<td><img style="width: 50px;" src="${entry.key.image }" /></td>
							<td><a href="#">${entry.key.type} ${entry.key.brand} ${entry.key.model} ${entry.key.color}</a></td>
							<td>
                                <form action="view-cart" method="get">
                                <input type="number" min="1" name="quantity" style="max-width: 100px;" value="${entry.value}"/>
                                <input type="hidden" name="product_id" value="${entry.key.id}"/>
                                <input type="hidden" name="action" value="update"/>
                                <input type="submit" value="Edit"/>
                                </form>
							</td>
							<td>${entry.key.price} <span>$</span></td>
							<td>${entry.key.price * entry.value} <span>$</span></td>
                            <td><a class="text-danger" href="view-cart?action=delete&product_id=1">Delete</a></td>
						</tr>		
						</c:forEach>
						<tr>
							<th></th>
							<td></td>
							<td></td>
							<td></td>
							<td>Total</td>
							<td>${total} <span>$</span></td>
                            <td></td>
						</tr>		
					</tbody>
				</table>
			</div> <!-- cart -->
		    <div class="col6">
		    <a href="#" onclick="history.back();" class="btn btn-primary">Back to shopping</a>
		    <c:if test="${user != null }">
		         <a href="checkout" class="btn btn-primary">Checkout</a>
		    </c:if>
		    <c:if test="${user == null }">
		        <div class="col-12 mt-3">
		             Checkout is available only for registered users. Please <a href="login">Sign In</a> or <a href="">Register</a> if you don't have account yet.
		        </div>
		    </c:if>
		    </div> <!-- cart related messages-->
		          
		          </c:if>
		          <c:if test="${empty cart}">
		                <div class="col-12">
		                    Your Shopping Cart is empty <a href="#" onclick="history.back();" class="link-primary">Back to shopping</a>
		                </div>
		          </c:if>
		       </c:when>
		       <c:otherwise>
		          <div class="col-12">
		                    Your Shopping Cart is empty <a href="#" onclick="history.back();" class="link-primary">Back to shopping</a>
		                </div>
		       </c:otherwise>
		    </c:choose>
			
		    
		</div>
	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
		crossorigin="anonymous"></script>
</body>
</html>