package edu.neu.cs5200.bookstore.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.neu.cs5200.bookstore.DAO.BookDAO;
import edu.neu.cs5200.bookstore.DAO.reviewDAO;
import edu.neu.cs5200.bookstore.DAO.userDAO;
import edu.neu.cs5200.bookstore.model.Review;
import edu.neu.cs5200.bookstore.model.User;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String bookid = request.getParameter("bookId");
		String userid = request.getParameter("userId");
		String stars = request.getParameter("stars");
		String comment = request.getParameter("comment");
		//String username = request.getParameter("username");
		int bookId=Integer.parseInt(bookid);
		int userId = Integer.parseInt(userid);
		
		userDAO userdao = new userDAO();
		User user = userdao.getUser(userId);
		
		reviewDAO reviewdao = new reviewDAO();
		Review review = new Review();
		
		review.setComments(comment);
		review.setRating(stars);
		
		reviewdao.createReview(userId, review, bookId);
					
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
