

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("text/html;charset=UTF-8");
	     PrintWriter out = response.getWriter();
	     
	     String login_email = request.getParameter("email_login");
	     String login_pass = request.getParameter("password_login");
	     
	     login_pass=Hashfn.hashfn(login_pass);
	     
	            
	            if(validation.Login_check(login_email, login_pass))
	            {
	            	//RequestDispatcher rs = request.getRequestDispatcher("Welcome");
	                //rs.forward(request, response);
	            	response.sendRedirect("https://www.youtube.com/watch?v=_sdn1B0N4k0");
	            }
	            else
	            {
	               out.println("Username or Password incorrect");
	               RequestDispatcher rs = request.getRequestDispatcher("index.html");
	               rs.include(request, response);
	            }
	            
	        
	     
	        
		
		
		//doGet(request, response);
	}

}
