package edu.neu.cs5200.bookstore.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.rmi.server.Dispatcher;
import edu.neu.cs5200.bookstore.DAO.BookDAO;
import edu.neu.cs5200.bookstore.DAO.userDAO;
import edu.neu.cs5200.bookstore.model.Book;
import edu.neu.cs5200.bookstore.model.User;

/**
 * Servlet implementation class likeServlet
 */
@WebServlet("/likeServlet")
public class likeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public likeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		//String action = request.getParameter("action");
		String title = request.getParameter("bookTitle");
		String author = request.getParameter("bookAuthor");
		String imgurl = request.getParameter("bookImgurl");
		String category = request.getParameter("bookCategory");
		String bookid = request.getParameter("bookId");
		//String imgurl = "testurl";
		
		Book newBook = new Book();
		newBook.setTitle(title);
		newBook.setAuthor(author);
		newBook.setImgurl(imgurl);
		newBook.setCategory(category);
		newBook.setBookId(bookid);
		
		BookDAO bookdao = new BookDAO();
		
			bookdao.createBook(newBook);
			
			
			userDAO userdao = new userDAO();
			
			User user = new User();
			user.setUsername(username);			
			boolean checkAdmin = userdao.verifyAdmin(user);					
			if (checkAdmin) {
				dispatcher = request.getRequestDispatcher("searchbookforadmin.jsp");
				dispatcher.forward(request, response);
			}else{
				dispatcher = request.getRequestDispatcher("searchbookforuser.jsp");
				dispatcher.forward(request, response);
				
			}
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
