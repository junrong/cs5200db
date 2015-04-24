package edu.neu.cs5200.bookstore.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.neu.cs5200.bookstore.DAO.userDAO;
import edu.neu.cs5200.bookstore.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		userDAO userdao = new userDAO();
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		boolean checkUser =  userdao.verifyUser(user);
		boolean checkAdmin = userdao.verifyAdmin(user);
		HttpSession session = request.getSession(true);
		if(checkAdmin) {
			session.setAttribute("currentUser", user);
			dispatcher = request.getRequestDispatcher("searchbookforadmin.jsp");
			dispatcher.forward(request, response);
		} 
		else if(checkUser) {
			session.setAttribute("currentUser", user);
			dispatcher = request.getRequestDispatcher("searchbookforuser.jsp");
			dispatcher.forward(request, response);
		} else {
			session.setAttribute("currentUser", null);
			dispatcher = request.getRequestDispatcher("login.jsp");
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
