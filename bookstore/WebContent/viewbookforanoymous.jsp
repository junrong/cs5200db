<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="edu.neu.cs5200.bookstore.DAO.*, edu.neu.cs5200.bookstore.model.*"%>

			<%String querybookid = request.getParameter("id"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="bookstore" content=" Your personalized bookstore" />
<meta name="author" content="book" />
<title><%=querybookid%></title>
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


	<div class="content row container-fluid">

	<hr class="featurette-divider">
	<!-- ==============content====================== -->
	

<input id="bookid" type="hidden" value="<%=querybookid%>" />  
   
	<form action="likeServlet">
	<div id="viewbookdetail">	
	</div>
	<table id="BookList">
	</table>
	<input type="hidden" name="bookTitle" id="bookTitle">
	<input type="hidden" name="bookAuthor" id="bookAuthor">
	<input type="hidden" name="bookImgurl" id="bookImgurl">
	<input type="hidden" name="bookCategory" id="bookCategory">
	<input type="hidden" name="bookID" id="bookID">
	         
     
          
<script >

 jQuery(init);

function init() {
	
	var bookid = jQuery("#bookid").val();
	//alert(bookid);
	var urlll="https://www.googleapis.com/books/v1/volumes/"+ bookid;
	//alert(urlll);
	jQuery
			.ajax({
				url : urlll,				
				dataType : "jsonp",
				success : handleResponse,
				error: function (xhr, status, err) {
	                document.getElementById('#BookList').innerHTML = '<br/><center><h2>The API is not responding, please try again.</h2></center>';
	            }
			});	
}

function handleResponse(book){
	//alert("woshiwoegoauoge");
	 document.getElementById('viewbookdetail').innerHTML = "";
	
	var id = book.id;
	
	var title = book.volumeInfo.title; 
	var author = book.volumeInfo.authors[0]; 
	var imgurl = book.volumeInfo.imageLinks.thumbnail; 
	var categoryobj = book.volumeInfo.categories;
	var description = book.volumeInfo.description;
	if (typeof categoryobj != 'undefined'){
		var category = book.volumeInfo.categories[0]		
	}else{
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
		+ '<tr><td><span style="color:blue;">Description:  </span><span class="description" value=""></span></td></tr>'		
		+ '<tr><td>&nbsp;</td></tr>';
		
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
	document.getElementById("bookTitle").value=title;
	document.getElementById("bookAuthor").value=author;
	document.getElementById("bookImgurl").value=imgurl;
	document.getElementById("bookCategory").value=category;
	
	// document.getElementById('viewbookdetail').innerHTML = theader + tbody + "<table>";
}

</script>

</form>
<!-- =========================================== -->


	<hr class="featurette-divider">

	</div>

</body>

<div id="footer_load"></div>

</html>