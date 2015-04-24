<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- ===============js=================== -->



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
<title>LOGIN</title>
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
						<li><a class="menu-top-active" href="index.jsp">Search</a></li>
						<li><a href="register.jsp">Sign Up</a></li>
						<li><a href="login.jsp">Login</a></li>
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
                    <h4 class="page-head-line">LOGIN PAGE</h4>

                </div>

            </div>
            <div class="row">
                <div class="col-md-12">
                    
                    <!-- =========================================== -->

							<form action="LoginServlet">

			<div id="login">
				<h1>Login</h1>
				<div class="login1">
					<table>
						<tr>
							<td><h2>username</h2></td>
							<td><input name="username" /></td>
						</tr>
						<tr>
							<td><h2>password</h2></td>
							<td><input name="password" type="password" /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><button name="" value="login">Login</button></td>
						</tr>
					</table>
				</div>
			</div>
			<div id="register">
				<table>
					<tr>
						<td> <a href="register.jsp">Please click SIGN UP</a>
					    </td>
					</tr>
				</table>
			</div>

		</form>



					<!-- =========================================== -->

                    <hr class="featurette-divider">
                </div>

            </div>
        </div>
    </div>
    <!-- CONTENT-WRAPPER SECTION END-->
<!-- =========================================== -->


</body>

<div id="footer_load"></div>
</html>