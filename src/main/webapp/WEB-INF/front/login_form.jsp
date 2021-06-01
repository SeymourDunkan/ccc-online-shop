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
<meta charset="UTF-8">
<title>Login Form</title>
</head>
<body>
	<div class="container">
		<div class="row">
		    <div class="col-4 offset-4" style="height: 100px;"></div>
			<div class="col-4 offset-4">
				<h1>Sign In</h1>
						<form method="post" action="login"
							class="row g-3 needs-validation" novalidate>
							<div class="col-12">
								<label for="email" class="form-label">Email:</label> <input
									type="email" class="form-control" id="email" name="email"
									value="email@example.com" required>
								<div class="invalid-feedback">Please provide a valid
									Email.</div>
							</div>
							<div class="col-12">
								<label for="password" class="form-label">Password:</label> <input
									type="password" class="form-control" id="password"
									name="password" value="334455tt" required>
								<div class="invalid-feedback">Please provide a valid
									Password.</div>
							</div>
							<div class="col-12">
								<button class="btn btn-primary" type="submit">Sign In</button>
							</div>
							
							<div class="col-12">
								<a class="btn ms-0 ps-0 text-primary" href="register-user">Don't have account yet? Register here</button>
							</div>	
						</form>
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