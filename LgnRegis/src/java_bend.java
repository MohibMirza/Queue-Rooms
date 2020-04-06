

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class java_bend
 */
@WebServlet("/java_bend")
public class java_bend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public java_bend() {
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
		
		
		//System.out.println("In here!");
	 response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        String regis_email = request.getParameter("email_regis");
        String  userid= request.getParameter("userid");
        String regis_pass = request.getParameter("password_regis");
        String login_email = request.getParameter("email_login");
        String login_pass = request.getParameter("password_login");
        
        
//        System.out.println(regis_email);
//        System.out.println(userid);
//        System.out.println(regis_pass);
        
        regis_pass=Hashfn.hashfn(regis_pass);
        		
        try {
            
        	//System.out.println("In here2!");
            // loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");
            
            //creating connection with the database 
            Connection con = DriverManager.getConnection
                        ("jdbc:mysql://localhost/factory?user=root&password=123456&serverTimezone=UTC");

            
            if(!validation.regis_check(regis_email))
            {
            	out.println("Email-ID already in use");
	            RequestDispatcher rs = request.getRequestDispatcher("index.html");
	            rs.include(request, response);
            	
            }
            else {
            
            PreparedStatement ps = con.prepareStatement
                        ("insert into Accounts (`Email`, `UserID`, `Password`) values('"+regis_email+"','"+userid+"','"+regis_pass+"')");

           // System.out.println("In here3!");
//            ps.setString(1, regis_email);
//            ps.setString(2, userid);
//            ps.setString(3, regis_pass);
            int i = ps.executeUpdate();
           // System.out.println("In here4!");
            if(i > 0) {
                out.println("You are sucessfully registered");
	            RequestDispatcher rs = request.getRequestDispatcher("index.html");
	            rs.include(request, response);
            }
            else
            {
            	out.println("Something went wrong");
            }
        
        }
        }
        catch(Exception se) {
            se.printStackTrace();
        }
		
		
		//doGet(request, response);
	}

}
