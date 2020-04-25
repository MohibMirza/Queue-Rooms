

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import add_to_firebase_stuff.User;
import b_end.firebase_info_getter;

/**
 * Servlet implementation class add_friend
 */
@WebServlet("/add_friend")
public class add_friend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public add_friend() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userid= request.getParameter("f_id");
		
		String my_id= request.getParameter("my_id");
		
		
		String friend_name=firebase_info_getter.friend_getter(userid);
		
		
		System.out.println("In add_friend, friend_name is: " +friend_name);
		
		friend_name= friend_name.substring(0,friend_name.indexOf("password=")-1);
		request.setAttribute("friend_name", friend_name);
		
		User.addFriends(my_id, userid);
		System.out.println("Back in add_friend going to dispatch");
		RequestDispatcher rs = request.getRequestDispatcher("zi/index.jsp");
        rs.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		
		//doGet(request, response);
	}

}
