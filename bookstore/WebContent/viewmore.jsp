<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="edu.neu.cs5200.bookstore.DAO.*, edu.neu.cs5200.bookstore.model.*, java.util.*"%>
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


		<%
		String id = request.getParameter("id");
	%>
	<%
		String querybookid = request.getParameter("bookid");
	%>
	<%
		String username = request.getParameter("username");
	int Id=Integer.parseInt(id); 
	BookDAO bookdao = new BookDAO();
	Book book = bookdao.getBookforId(Id);
	reviewDAO reviewdao = new reviewDAO();
	List<Book> books = bookdao.listAllBooks();
	List<Review> reviews = reviewdao.listNReviewsForBook(5, Id);
	%>
	<%
		userDAO userDao = new userDAO();
			int userId = userDao.findUserId(username);
			User user = userDao.getUser(userId);
			boolean checkAdmin = userDao.verifyAdmin(user);
			String goAdmin = "searchbookforuser.jsp";
			if (checkAdmin) {
		goAdmin = "searchbookforadmin.jsp";
			}
	%>



	<div id="header_load"></div>

<!-- LOGO HEADER END-->
<section class="menu-section">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="navbar-collapse collapse ">
					<ul id="menu-top" class="nav navbar-nav navbar-right">
						<li><a  href="searchbookforuser.jsp?username=<%=username%>">Search</a></li>
						<li><a href="favoritebook1.jsp?username=<%=username%>">FavoriteBook</a></li>
						<li><a href="updateprofile1.jsp?username=<%=username%>">Update Profile</a></li>
						<li><a class="menu-top-active" href="userprofile1.jsp?username=<%=username%>">View Profile</a></li>	
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
                    <h4 class="page-head-line">User profile</h4>

                </div>

            </div>
            <div class="row">
                <div class="col-md-12">
                    
                    <!-- =========================================== -->
					


	<input id="bookid" type="hidden" value="<%=querybookid%>" />


	<form action="ShoppingCartServlet">
		<div id="viewbookdetail"></div>
		<table id="BookList">
		</table>
		
			
			<input type="hidden" name="username" value="<%=username%>"></input> 
		<input type="hidden" name="bookId" value="<%=id%>"></input>
		<input type="hidden" name="userId" value="<%=userId%>"></input>

		<script>

 jQuery(init);

function init() {
	
	var bookid = "<%=book.getBookId()%>";
				//alert(bookid);
				var urlll = "https://www.googleapis.com/books/v1/volumes/"
						+ bookid;
				//alert(urlll);
				jQuery
						.ajax({
							url : urlll,
							dataType : "jsonp",
							success : handleResponse,
							error : function(xhr, status, err) {
								document.getElementById('#BookList').innerHTML = '<br/><center><h2>The API is not responding, please try again.</h2></center>';
							}
						});
			}

			function handleResponse(book) {
				//alert("woshiwoegoauoge");
				document.getElementById('viewbookdetail').innerHTML = "";

				var id = book.id;

				var title = book.volumeInfo.title;
				var author = book.volumeInfo.authors[0];
				var imgurl = book.volumeInfo.imageLinks.thumbnail;
				var categoryobj = book.volumeInfo.categories;
				var description = book.volumeInfo.description;
				if (typeof categoryobj != 'undefined') {
					var category = book.volumeInfo.categories[0]
				} else {
					var category = "No Category!";
				}
				//alert(category);

				var list = jQuery("#BookList");
				list.empty();

				var liStr = '<tr id="1234"><td><img height="166" src=""/></td></tr>'
						+ '<tr><td><span style="color:blue;">bookID:      </span><span class="bookid" value="">Star Wars Episode V</span></td></tr>'
						+ '<tr><td><span style="color:blue;">Title:      </span><span class="title" value="">Star Wars Episode V</span></td></tr>'
						+ '<tr><td><span style="color:blue;">Author:     </span><span class="author" value="">Star Wars Episode V</span></td></tr>'
						+ '<tr><td><span style="color:blue;">Category:    </span><span class="category" value=""></span></td></tr>'
						+ '<tr><td><span style="color:blue;">Description:  </span><span class="description" value=""></span><tr><td>'
						+ '<tr><td><p><button class = "btn btn-info" name="action" value="addtoshoppingcart" >Add To ShoppingCart</button></p> <tr><td>'
						//+ '<tr><td><textarea rows="4" cols="50" name="comments" value="" /></td></tr>'
						+ '<tr><td>&nbsp;</td></tr>';
				//+ '<tr><td><button name="action" value="addcomment" class="">AddComment</button></td></tr>';
				//+ '<tr><td>&nbsp;</td></tr>'; 
				//list.append(liStr);
				var li = jQuery(liStr);
				li.attr("id", id);
				li.find("img").attr("src", imgurl);
				li.find(".bookid").html(id);
				li.find(".title").html(title);
				li.find(".author").html(author);
				li.find(".category").html(category);
				li.find(".description").html(description);
				//li.find("button").attr("value", id); 

				list.append(li);
				document.getElementById("bookTitle").value = title;
				document.getElementById("bookAuthor").value = author;
				document.getElementById("bookImgurl").value = imgurl;
				document.getElementById("bookCategory").value = category;

				// document.getElementById('viewbookdetail').innerHTML = theader + tbody + "<table>";
			}
		</script>

	</form>

	<form action="CommentServlet">
		<div id="review">
<h1>My Comments for this book are as following:</h1>
			
				<%
				int i=0;
					for (Review review : reviews) {
						User user1 = review.getUserBean();
						String username1 = user.getUsername();
						i++;
				%>

				
					<p><h2><%=username%>:</h2>
					Comment<%=i %>. <%=review.getComments()%><p>
			
			<%
				}
			%>
		</div>
		<br />
		<div id="comment" style="text-align: center;">
			<textarea name="comment"
				class="form-control" rows="3">
	</textarea>
			<br /> <select name="stars" class="form-control">
				<option value="1">1 star</option>
				<option value="2">2 star</option>
				<option value="3" selected="selected">3 star</option>
				<option value="4">4 star</option>
				<option value="5">5 star</option>
			</select>
			<button name="action" class="btn btn-info btn-lg" value="comment">submit comment</button>
			<br />

		</div>
		</div>
		<input type="hidden" name="username" value="<%=username%>"></input> 
		<input type="hidden" name="bookId" value="<%=id%>"></input>
		<input type="hidden" name="userId" value="<%=userId%>"></input>
		</div>
	</form>

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