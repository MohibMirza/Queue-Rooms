package usc.edu.eq.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import usc.edu.eq.FirebaseQuery;
import usc.edu.eq.User;
import usc.edu.eq.room.Room;

@WebServlet("/Queue")
public class Queue extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter(); // THINGS TO NOTE
		
		System.out.println("Test");
		
		//String id = (String) request.getParameter("roomid");
		String id = "ac2ac6e8-6158-4de0-94d6-1d3263d0a631";
		
		Room room = (Room) FirebaseQuery.queryRoomID(id);
		LinkedList<String> queueMembers = room.getMainQueue();
	
		int size = queueMembers.size();
		System.out.println(size);
		
		request.setAttribute("queueMembers", queueMembers);
		request.setAttribute("queueSize", size);
		
		for(int i = 0; i < size; i++) {
			 User u = FirebaseQuery.queryUserID(queueMembers.get(i));
			 if(u == null) continue;
			
			out.println("<tr>");
			out.println("<td>" + (i+1) + "</td>");
			out.println("<td>" + u.getUsername() + "</td>");
			out.println("<td>" + "5 mins" + "</td>");
			out.println("</tr>");
		}
		
//		String college = request.getParameter("college");
//		college = college.toLowerCase();
//		if (college.equals("usc") || college.equals("ucla") || college.equals("berkeley") || college.equals("stanford")) {
//			out.println("<img src=\"" + college + ".jpg\">");
//		}
//		else {
//			out.println("<h3>I don't know that college.</h3>");
//		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

//<tr>
//<td>1</td>
//<td>Abby B</td>
//<td>5 mins</td>
//</tr>
//<tr>
//<td>2</td>
//<td>Bobby C</td>
//<td>15 mins</td>
//</tr>
//<tr>
//<td>3</td>
//<td>Carly D</td>
//<td>25 mins</td>
//</tr>
//<tr>
//<td>4</td>
//<td>Danny E</td>
//<td>35 mins</td>
//</tr>
//<tr>
//<td>5</td>
//<td>Edward F</td>
//<td>45 mins</td>
//</tr>
