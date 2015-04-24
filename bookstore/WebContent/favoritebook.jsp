<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="edu.neu.cs5200.bookstore.DAO.*, edu.neu.cs5200.bookstore.model.*, java.util.*"%>
<!-- ===============js=================== -->
<%
	String username= request.getParameter("username");

		userDAO userDao = new userDAO();
		int userId = userDao.findUserId(username);
		User user = userDao.getUser(userId);
		boolean checkAdmin = userDao.verifyAdmin(user);
		String goAdmin = "searchbookforuser.jsp";
		if (checkAdmin) {
	goAdmin = "searchbookforadmin.jsp";
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
<script src="js/view_book.js"></script>


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
							<li><a href="searchbookforadmin.jsp?username=<%=username%>">Search</a></li>
							<li><a class="menu-top-active"
								href="favoritebook.jsp?username=<%=username%>">FavoriteBook</a></li>
							<li><a href="createbook.jsp?username=<%=username%>">ManagementBook</a></li>
							<li><a href="updateprofile.jsp?username=<%=username%>">Update
									Profile</a></li>
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
					<h4 class="page-head-line">The Book You liked ~</h4>

				</div>

			</div>

			<%
				reviewDAO reviewdao = new reviewDAO();
				List<Review> reviews = reviewdao.listReviewsForUser(userId);
				for (Review review : reviews) {
					Book book = review.getBookBean();
			%>
			<div class="row pading_bo">
				<form name="formname1" action="viewmore.jsp">
					<table style="float: left;">
						<tr>
							<td><img style="clear: both; text-align: center;"
								height="100" src="<%=book.getImgurl()%>" /></td>
						</tr>
						<tr>
							<td><button name="action" value="description"
									style="background-color: transparent; border: 0px; cursor: pointer;">
									<h3><%=book.getTitle()%></h3>
								</button></td>
						</tr>
						<tr>
							<td><input type="hidden" name="title"
								value="<%=book.getTitle()%>" /></td>
						</tr>
						<tr>
							<td><input type="hidden" name="username"
								value="<%=username%>" /></td>
						</tr>
						<tr>
							<td><input type="hidden" name="id" value="<%=book.getId()%>" /></td>
						</tr>
						<tr>
							<td><input type="hidden" name="bookid"
								value="<%=book.getBookId()%>" /></td>
						</tr>

						<tr>
							<td><button class="btn btn-info" name="action"
									value="detail">see more detail</button></td>
						</tr>
					</table>
				</form>
			</div>
			<%
				}
			%>


		</div>
	</div>
</body>

<div id="footer_load"></div>
</html>