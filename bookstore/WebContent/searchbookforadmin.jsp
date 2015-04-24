<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="edu.neu.cs5200.bookstore.DAO.*, edu.neu.cs5200.bookstore.model.*,
    java.util.*"%>
<!-- ===============js=================== -->


<%
	String username = request.getParameter("username");
%>

<%
	userDAO userDao = new userDAO();
int userId = userDao.findUserId(username);
User user = userDao.getUser(userId);
boolean checkAdmin = userDao.verifyAdmin(user);
String goDeveloper = "searchbookforuser.jsp";
if (checkAdmin) {
goDeveloper = "searchbookforadmin.jsp";
}
%>

<!-- ==================================== -->
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="bookstore" content=" Your personalized bookstore" />
<meta name="author" content="book" />
<!--[if IE]>
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <![endif]-->
<title>SIGN UP</title>
<!-- BOOTSTRAP CORE STYLE  -->
<!-- HTML5 Shiv and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
<!-- FOOTER SECTION END-->
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FONT AWESOME ICONS  -->
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="assets/css/bootstrap-theme.min.css">
<!-- CUSTOM STYLE  -->
<link href="assets/css/style.css" rel="stylesheet" />
<link href="assets/css/custom.css" rel="stylesheet" />
<!-- JAVASCRIPT AT THE BOTTOM TO REDUCE THE LOADING TIME  -->
<!-- CORE JQUERY SCRIPTS -->
<script src="assets/js/jquery-1.11.1.js"></script>
<!-- BOOTSTRAP SCRIPTS  -->
<script src="assets/js/bootstrap.js"></script>
<!-- CUSTOM SCRIPTS  -->
<script src="js/custom.js"></script>
<script src="js/find_book.js"></script>


<script>
	$(function() {
		$("#header_load").load("header.html");
		$("#footer_load").load("footer.html");
	});
</script>

</head>
<body>



	<div id="header_load"></div>
	<!-- LOGO HEADER END-->
	<section class="menu-section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="navbar-collapse collapse ">
						<ul id="menu-top" class="nav navbar-nav navbar-right">
							<li><a class="menu-top-active"
								href="searchbookforadmin.jsp?username=<%=username%>">Search</a></li>
							<li><a href="favoritebook.jsp?username=<%=username%>">FavoriteBook</a></li>
							<li><a href="createbook.jsp?username=<%=username%>">ManagementBook</a></li>
							<li><a href="updateprofile.jsp?username=<%=username%>">Update Profile</a></li>
							<li><a href="userprofile.jsp?username=<%=username%>">Profile</a></li>
							<li><a href="index.jsp">Logout</a>
						</ul>
					</div>
				</div>

			</div>
		</div>
	</section>
	<!-- MENU SECTION END-->


	<!-- ==============content====================== -->

	<div class="content-wrapper">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h4 class="page-head-line">
						Hello, Administrator
						<%=request.getParameter("username")%></h4>

				</div>
			</div>
			<div class="row">
				<div class="col-md-12">

					<!-- =========================================== -->
					<form action="AccessServlet">
						<button class="btn btn-info" id="username" name="username"
							value="<%=username%>">About me</button>

					</form>

					<form class="text-center" id="form1">

						<div>
							<h1 class="text-center" id="search_guide">Search Your
								Favorite Book</h1>

							<p>
								<input type="text" id="title" placeholder="Search by book title"
									size="35" />&nbsp;&nbsp;
								<button class="btn btn-info btn-lg" type="button"
									id="search_title_button">Search</button>
							</p>

						</div>
					</form>

					<div class="row col-md-11 col-md-offset-1">
						<div id="searchResults"></div>
					</div>


					<hr />
					<h4 class="page-head-line">List All the books from database~</h4>
			
					
						<%
							BookDAO bookdao = new BookDAO();
							List<Book> books = bookdao.listAllBooks();

							for (int m = 0; m < books.size(); m++) {
								Book book = books.get(m);
						%>
					<form action="DeleteServlet">
						<input type="hidden" name="deletetitle"
							value="<%=book.getTitle()%>" /> <input type="hidden"
							name="username" value="<%=request.getParameter("username")%>" />
						<input type="hidden" name="deletebookId" value="<%=book.getId()%>" />


						<div class="row col-md-11 col-md-offset-1 pading_bo">
							<div class="col-md-12">
								<div class="col-md-4">
									<img src="<%=book.getImgurl()%>">
								</div>

								<div class="col-md-6">
									
										<h3><%=book.getTitle()%></h3>
								
								</div>

								<div class="col-md-2">
									<button class="btn btn-info" name="action" value="edit">Edit</button>
									<button class="btn btn-danger" name="action" value="delete">Delete</button>
								</div>

							</div>
						</div>						
					</form>
					<%
							}
						%>

				</div>

			</div>
		</div>
	</div>
	<!-- CONTENT-WRAPPER SECTION END-->
	<!-- =========================================== -->
	<!-- =========================================== -->

	<hr class="featurette-divider">

	<!-- Carousel
    ================================================== -->
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
			<li data-target="#myCarousel" data-slide-to="3"></li>
		</ol>
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<img class="img-responsive" src="assets/img/01.jpg"
					alt="First slide">
				<div class="container">
					<div class="carousel-caption">

						<h3>Looking for a book? Here's Where To Look!</h3>

					</div>
				</div>
			</div>
			<div class="item">
				<img src="assets/img/02.jpg" alt="Second slide">
				<div class="container">
					<div class="carousel-caption">
						<h1>Insight Reviews</h1>
						<h3>review your favorite book</h3>

					</div>
				</div>
			</div>
			<div class="item">
				<img src="assets/img/03.jpg" alt="Third slide">
				<div class="container">
					<div class="carousel-caption">
						<h1>Love your book</h1>
						<h3>Check out what books most people like</h3>

					</div>
				</div>
			</div>
			<div class="item">
				<img src="assets/img/04.jpg" alt="Fourth slide">
				<div class="container">
					<div class="carousel-caption">

						<h3>We love your feedback. Don't hesitate to contact us!</h3>

					</div>
				</div>
			</div>

		</div>
		<a class="left carousel-control" href="#myCarousel" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
	<!-- /.carousel -->



</body>

<div id="footer_load"></div>
</html>