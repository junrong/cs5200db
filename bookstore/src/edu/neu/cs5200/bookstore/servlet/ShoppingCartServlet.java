package edu.neu.cs5200.bookstore.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.neu.cs5200.bookstore.DAO.ShoppingcartDAO;
import edu.neu.cs5200.bookstore.DAO.reviewDAO;
import edu.neu.cs5200.bookstore.DAO.userDAO;
import edu.neu.cs5200.bookstore.model.Review;
import edu.neu.cs5200.bookstore.model.Shoppingcart;
import edu.neu.cs5200.bookstore.model.User;

/**
 * Servlet implementation class ShoppingCartServlet
 */
@WebServlet("/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCartServlet() {
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
		//String number = request.getParameter("number");
		
		int bookId=Integer.parseInt(bookid);
		int userId = Integer.parseInt(userid);
		int number = 1;
		
		userDAO userdao = new userDAO();
		User user = userdao.getUser(userId);
		
		ShoppingcartDAO cartdao = new ShoppingcartDAO();
		Shoppingcart cart = new Shoppingcart();
		
		cart.setNumber(1);
		cartdao.createCart(userId, cart, bookId);
		
		dispatcher = request.getRequestDispatcher("searchbookforuser.jsp");
		dispatcher.forward(request, response);
		
		
		
		
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
