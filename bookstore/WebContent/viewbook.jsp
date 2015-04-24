<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- ===============js=================== -->

	<%
		String querybookid = request.getParameter("id");
	String cur_username = request.getParameter("username");
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
<title><%=querybookid%></title>
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
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>


<script>
	$(function() {
		$("#header_load").load("header.html");
		$("#footer_load").load("footer.html");
	});
</script>

</head>
<body>



	<div id="header_load"></div>


	<div class="content row container-fluid">

	<hr class="featurette-divider">

<!-- ==============content====================== -->

	<form action="likeServlet">
		<div id="viewbookdetail"></div>
		<table id="BookList">
		</table>
		<input type="hidden" name="bookTitle" id="bookTitle">
		 <input type="hidden" name="bookAuthor" id="bookAuthor"> 
		 <input type="hidden" name="bookImgurl" id="bookImgurl"> 
		 <input type="hidden" name="bookCategory" id="bookCategory"> 
		<input type="hidden" name="username" value="<%=cur_username%>">
		<input type="hidden" name="bookId" id="book_id">
		
		<script>
 jQuery(init);

function init() {
	
	var bookid = "<%=querybookid%>";
				//alert(bookid);
				var urlll = "https://www.googleapis.com/books/v1/volumes/"+ bookid;
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

				var liStr = '<tr id="1234"><td><img class ="img_single" src=""/><p><button class = "btn btn-info" name="action" value=" LIKE IT " >Like</button></p></td></tr>'
						+ '<tr><td><span style="color:blue;">bookID:      </span><span class="bookid" value="">Star Wars Episode V</span></td></tr>'
						+ '<tr><td><span style="color:blue;">Title:      </span><span class="title" value="">Star Wars Episode V</span></td></tr>'
						+ '<tr><td><span style="color:blue;">Author:     </span><span class="author" value="">Star Wars Episode V</span></td></tr>'
						+ '<tr><td><span style="color:blue;">Category:    </span><span class="category" value=""></span></td></tr>'
						+ '<tr><td><span style="color:blue;">Description:  </span><span class="description" value=""></span></td></tr>'
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
				document.getElementById("book_id").value=id;

				 //document.getElementById('viewbookdetail').innerHTML = theader + tbody + "<table>";
			}
		</script>

	</form>

<!-- =========================================== -->


	<hr class="featurette-divider">

	</div>

</body>

<div id="footer_load"></div>
</html>