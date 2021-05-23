<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>Admin Add Product</title>
</head>
<body>
	<div class="container mt-5">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">

			<div class="container-fluid">
				<a class="navbar-brand"
					href="${pageContext.request.contextPath}/home"> <svg
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
				<div class="collapse navbar-collapse justify-content-between" id="navbarNav">
					<ul class="navbar-nav">
					    <li class="nav-item">
						    <a class="nav-link" href="dashboard">Адмін Панель</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="user-list">Користувачі</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="order-list">Замовлення</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="product-list">Товари</a>
						</li>
						<li class="nav-item">
						    <a class="nav-link" href="add-product">Додати Товар</a>
						</li>
						
					</ul>
					<ul class="navbar-nav">
					   	<li class="nav-item">
						    <a class="nav-link" href="logout">Log Out</a>
						</li>
					</ul>
				</div>
			</div>
		</nav>
	</div>

	<div class="container">
		<div class="row">
		<div class="col-8 offset-4" style="height: 100px;"></div>
			<div class="col-8 offset-2">
				<h1>Add Product</h1>
				<c:choose>
					<c:when test="${ errorMessages == null }">
						<form method="post" action="add-product"
							class="row g-3 needs-validation" novalidate>
							<div class="col-6">
								<label for="type" class="form-label">Type:</label> <input
									type="text" class="form-control" id="type" name="type"
									value="Черевик" required>
								<div class="invalid-feedback">Please provide a valid
									Type.</div>
							</div>
							<div class="col-6">
								<label for="brand" class="form-label">Brand:</label> <input
									type="text" class="form-control" id="brand" name="brand"
									value="Gino Rossi" required>
								<div class="invalid-feedback">Please provide a valid
									Brand.</div>
							</div>
							<div class="col-6">
								<label for="model" class="form-label">Model:</label> <input
									type="text" class="form-control" id="model" name="model"
									value="RP-51" required>
								<div class="invalid-feedback">Please provide a valid
									Model.</div>
							</div>
							<div class="col-6">
								<label for="material" class="form-label">Material:</label> <input
									type="text" class="form-control" id="material" name="material"
									value="Шкіра" required>
								<div class="invalid-feedback">Please provide a valid
									Material.</div>
							</div>
							<div class="col-6">
								<label for="color" class="form-label">Color:</label> <input
									type="text" class="form-control" id="color" name="color"
									value="Чорний" required>
								<div class="invalid-feedback">Please provide a valid
									Color.</div>
							</div>
							<div class="col-6">
								<label for="image" class="form-label">Image url:</label> <input
									type="text" class="form-control" id="image" name="image"
									value="https://shop.ccc.eu/media/cache/gallery/rc/smbocjmy/images/99/997449/2/2/2230008021082_01_pl.jpg" required>
								<div class="invalid-feedback">Please provide a valid
									Image url.</div>
							</div>
							<div class="col-6">
								<label for="price" class="form-label">Price (USD):</label> <input
									type="number" step="0.01" class="form-control" id="price" name="price"
									value="0" required>
								<div class="invalid-feedback">Please provide a valid
									Price.</div>
							</div>
							<div class="col-6">
								<label for="category" class="form-label">Category:</label> <select
									class="form-select" id="category" name="category" required>
									<option selected disabled value="">Choose...</option>
									<option value="13">Uncategorized</option>
									<option value="9">Women</option>
									<option value="10">Men</option>
									<option value="11">Children</option>
									<option value="12">Begs</option>
								</select>
								<div class="invalid-feedback">Please select a valid Category.</div>
							</div>
							<div class="col-6">
								<button class="btn btn-primary" type="submit">Add Product</button>
							</div>
						</form>

					</c:when>
					<c:otherwise>
						<!-- form with errors -->
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<!-- row -->
		<c:if test="${ errorMessages != null }">
			<div class="row">
				<div class="col-4 offset-4">
					<ul class="text-danger mt-3">
						<c:forEach var="message" items="${errorMessages}">
							<li>${message}</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</c:if>

	</div>
	<!-- container -->
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
		crossorigin="anonymous"></script>
		<script type="text/javascript">
		(function() {
			'use strict'

			// Fetch all the forms we want to apply custom Bootstrap validation styles to
			var forms = document.querySelectorAll('.needs-validation')

			// Loop over them and prevent submission
			Array.prototype.slice.call(forms).forEach(function(form) {
				form.addEventListener('submit', function(event) {
					if (!form.checkValidity()) {
						event.preventDefault()
						event.stopPropagation()
					}

					form.classList.add('was-validated')
				}, false)
			})
		})()
	</script>
</body>
</html>