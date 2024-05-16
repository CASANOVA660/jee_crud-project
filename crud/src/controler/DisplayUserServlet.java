package controler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEANS.user;
import dao.userdao;
/**
 * Servlet implementation class DisplayUserServlet
 */
@WebServlet("/DisplayUserServlet")
public class DisplayUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private userdao dao = new userdao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve all users from the database
		List<user> userList = dao.getAllUsers();
	    int userCount = dao.countUsers();

	    // Forward the results to the JSP page
	    request.setAttribute("userList", userList);
	    request.setAttribute("userCount", userCount);

        // Forward the request to the JSP to generate the HTML table
        request.getRequestDispatcher("/WEB-INF/displayUsers.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
