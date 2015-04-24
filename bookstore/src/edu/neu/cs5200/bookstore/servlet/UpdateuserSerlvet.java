package edu.neu.cs5200.bookstore.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.neu.cs5200.bookstore.DAO.userDAO;
import edu.neu.cs5200.bookstore.model.User;

/**
 * Servlet implementation class UpdateuserSerlvet
 */
@WebServlet("/UpdateuserSerlvet")
public class UpdateuserSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateuserSerlvet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter("firstName");
		String lastname = request.getParameter("lastName");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		Integer permisson =1;
		
		/*User newUser = new User();
		newUser.setFirstname(firstname);
		newUser.setLastname(lastname);
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setEmail(email);
		newUser.setPhone(phone);
		newUser.setPermission(permisson);
		*/
		userDAO userdao = new userDAO();
		
		User user = new User();
		user.setUsername(username);
		//user.setPassword(password);
		//boolean checkUser =  userdao.verifyUser(user);
		boolean checkAdmin = userdao.verifyAdmin(user);
		
		//boolean success = userdao.createUser(newUser);
		/*int userId = userdao.findUserId(username);
		User user = userdao.getUser(userId);
		boolean checkAdmin = userdao.verifyAdmin(user);*/
		//String goDeveloper = "searchbookforuser.jsp";
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
