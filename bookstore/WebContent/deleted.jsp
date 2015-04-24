<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="edu.neu.cs5200.bookstore.DAO.*, edu.neu.cs5200.bookstore.model.*,
    java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Hello, Administrator <%=request.getParameter("username") %></h1>
<p>You have successfully deleted the book: <%= request.getParameter("deletetitle") %></p>
 <form id="form1">
  
       <div>
           <br />
          <center><h1 id="search_guide">Search Book by Book title</h1><br /></center> 
          <center><input type="text" id="title" placeholder="search title" size="35"/>
            <button type="button" id="search_title_button">Search</button></center> 
        </div>
        <div id="searchResults"></div> 
        
        
         <table style="clear:both; width:100%; text-align:center;padding:13px;">
				 <th><h2>List all computers books</h2></th>						
				 <tr>
				<%
					BookDAO bookdao = new BookDAO();
					List<Book> books = bookdao.listAllBooks();					
					for (int m=0; m<=2; m++) {
						Book book=books.get(m);
				%>
				<td style="text-align:center;padding:36px;">
					<form name="formname1" action="DeleteServlet" >				
					<table>
						<tr><td style="text-align:center;"><img style="clear:both; text-align: center;" height="126" src="<%=book.getImgurl()%>" /></td></tr>
						<tr><td style="text-align:center;"><button name="action" value="description"
								style="background-color: transparent; border: 0px; cursor: pointer;">
								<h3><%=book.getTitle()%></h3>
							</button></td></tr>
						<tr><td ><input type="hidden" name="deletetitle"
							value="<%=book.getTitle()%>" /></td></tr>
						<tr><td><input type="hidden" name="username"
							value="<%=request.getParameter("username")%>" /></td></tr>
						<tr><td><input type="hidden" name="deletebookId" value="<%=book.getId()%>" /></td></tr>
						<tr><td style="text-align:center;"><button name="action" value="delete">Delete</button></td></tr>
						<tr><td style="text-align:center;"><button name="action" value="edit">Edit</button></td></tr>
						
					</table>
					</form>
					
					</td>			
				<%
					}				
				%>
				</tr>
				</table>
				
                       
    </form>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
    <script src="js/find_book.js"></script>
</body>
</html>