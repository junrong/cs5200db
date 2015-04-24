<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="edu.neu.cs5200.bookstore.DAO.*, edu.neu.cs5200.bookstore.model.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<table id="shopcart_table">
<tr>
<th>
book
</th>
<th>
Number
</th>
</tr>
</table>

<script>


var table = document.getElementById("shopcart_table");

<%  String user_id = request.getParameter("userid");
int userid = Integer.parseInt(user_id);
%>

<%  ShoppingcartDAO shoppingcartDAO = new ShoppingcartDAO();
	List<Shoppingcart> listshopcart = shoppingcartDAO.readAllBooksByUserID(userid);
	
	for(int i=0; i<listshopcart.size(); i++) {
%>		
		var tr = document.createElement("tr");
		var tdbook = document.createElement("td");
		var tdnum = document.createElement("td");
		
		tdbook.innerHTML="<%= listshopcart.get(i).getBook().getId() %>";
		tdnum.innerHTML="<%= listshopcart.get(i).getNumber() %>"
		
		tr.appendChild(tdbook);
		tr.appendChild(tdnum);
		
		table.appendChild(tr);

<%
	}

%>

</script>

</body>
</html>

