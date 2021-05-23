<div class="container mb-4 ">
	  <c:if test="${user != null }">
		<header class="py-3">
			<div class="row flex-nowrap justify-content-between align-items-center">
				<div class="col-4 pt-1 d-flex justify-content-start align-items-center">
					<a class="btn" href="#">Ukr</a> <a class="btn" href="#">Eng</a>
				</div>
				<div class="col-8 pt-1 d-flex justify-content-end align-items-center">
					<div class="dropdown">
                         <button class="btn dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                         <i class="bi bi-person-circle"></i>
                         <c:out value="${user.firstName} ${user.lastName}" />
                         </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                            <li><a class="dropdown-item" href="#">MyOrders</a></li>
                            <li><a class="dropdown-item" href="user-logout">Logout</a></li>
                        </ul>
                  </div> 
                  <a class="btn p-1" href="view-cart" role="button"><i class="bi bi-cart"></i>Shopping cart<span class="badge bg-secondary ms-1">9</span></a>
				</div>
			</div>
		</header>
	</c:if>
	<c:if test="${user == null }">
		<header class="py-3">
			<div class="row flex-nowrap justify-content-between align-items-center">
				<div class="col-4 pt-1 d-flex justify-content-start align-items-center">
					<a class="btn" href="#">Ukr</a><a class="btn" href="#">Eng</a>
				</div>
				<div class="col-8 pt-1 d-flex justify-content-end align-items-center">
					<a class="btn" href="login">Sign In</a><a class="btn" href="view-cart"><i class="bi bi-cart"></i>Shopping Cart</a>
				</div>
			</div>
		</header>
	</c:if>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
		
			<div class="container-fluid">
				<a class="navbar-brand" href="#">
                     <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 80 27" width="80px" 
                     height="27px"><title>CCC</title><g id="Page-1" stroke="none" 
                     stroke-width="1" fill="none" fill-rule="evenodd"><g id="logo" fill="#E9500E" 
                     fill-rule="nonzero"><g id="Group">
                     <path d="M0.11194,13.51286 L0.11194,13.43915 C0.0481049964,9.86134377 1.44891843,6.41280458 3.98964001,3.89298194 C6.53036158,1.37315929 9.99035305,0.00087657352 13.56751,0.09426 C17.3724373,-0.0954475669 21.0763539,1.35390319 23.74205,4.07558 L20.12928,8.24128 C18.396666,6.44286168 16.0269445,5.39699896 13.53062,5.329 C9.18062,5.329 6.04714,8.94169 6.04714,13.36542 L6.04714,13.43913 C6.04714,17.86286 9.10682,21.54933 13.53062,21.54933 C16.47977,21.54933 18.28615,20.36967 20.31362,18.52647 L23.92636,22.176 C21.2943255,25.2551019 17.3934688,26.9541071 13.3463,26.78411 C9.81264687,26.8414023 6.40739335,25.4603727 3.91187534,22.9578986 C1.41635733,20.4554245 0.0448117789,17.04634 0.11194,13.51286 L0.11194,13.51286 Z" id="Path"></path><path d="M27.99361,13.51286 L27.99361,13.43915 C27.9297722,9.86134471 29.3305834,6.41280543 31.8713034,3.89298246 C34.4120234,1.3731595 37.8720139,0.000876476198 41.44917,0.09426 C45.2540979,-0.0954514658 48.9580161,1.3538999 51.62371,4.07558 L48.011,8.24128 C46.27837,6.4428491 43.908625,5.3969855 41.41228,5.329 C37.06228,5.329 33.9288,8.94169 33.9288,13.36542 L33.9288,13.43913 C33.9288,17.86286 36.98848,21.54933 41.41228,21.54933 C44.36143,21.54933 46.16781,20.36967 48.19528,18.52647 L51.808,22.176 C49.1759814,25.2550886 45.2751489,26.9540929 41.228,26.78411 C37.6943417,26.8414105 34.2890803,25.4603846 31.7935558,22.9579095 C29.2980312,20.4554345 27.9264815,17.0463453 27.99361,13.51286 Z" id="Path"></path><path d="M55.8197,13.51286 L55.8197,13.43915 C55.755865,9.86134377 57.1566784,6.41280458 59.6974,3.89298194 C62.2381216,1.37315929 65.6981131,0.00087657352 69.27527,0.09426 C73.0801947,-0.0954498599 76.7841091,1.35390148 79.4498,4.07558 L75.837,8.24128 C74.1043958,6.44287125 71.7346905,5.39700936 69.23838,5.329 C64.88838,5.329 61.75489,8.94169 61.75489,13.36542 L61.75489,13.43913 C61.75489,17.86286 64.81458,21.54933 69.23838,21.54933 C72.18753,21.54933 73.99391,20.36967 76.02138,18.52647 L79.63412,22.176 C77.0020855,25.2551019 73.1012288,26.9541071 69.05406,26.78411 C65.5204069,26.8414023 62.1151534,25.4603727 59.6196353,22.9578986 C57.1241173,20.4554245 55.7525718,17.04634 55.8197,13.51286 L55.8197,13.51286 Z" id="Path"></path></g></g></g></svg>
                </a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNav"
					aria-controls="navbarNav" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav">
						<li class="nav-item">
						    <a class="nav-link" href="${pageContext.request.contextPath}/product-list?category_id=9">Жіноче</a>
						</li>
						<li class="nav-item">
						    <a class="nav-link" href="${pageContext.request.contextPath}/product-list?category_id=10">Чоловіче</a>
						</li>
						<li class="nav-item">
						    <a class="nav-link" href="${pageContext.request.contextPath}/product-list?category_id=11">Дітяче</a>
						</li>
						<li class="nav-item">
						    <a class="nav-link" href="${pageContext.request.contextPath}/product-list?category_id=12">Сумки</a>
						</li>
					</ul>
				</div>
			</div>
		</nav>
	</div>