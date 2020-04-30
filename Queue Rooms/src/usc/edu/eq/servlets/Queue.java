package usc.edu.eq.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import usc.edu.eq.FirebaseQuery;
import usc.edu.eq.User;
import add_to_firebase_stuff.*;

import com.google.api.core.ApiFuture;
//import org.slf4j.impl.StaticLoggerBinder;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

@WebServlet("/Queue")
public class Queue extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter(); // THINGS TO NOTE
		
		System.out.println("Attempting to acquire the room.");
		
		//String id = (String) request.getParameter("roomid");
		String id = "db5854bc-9c4c-4de8-83c1-16b45ddce0b9";
		
		Room room = FirebaseQuery.queryRoomID(id);
		List<String> queueMembers = room.mainQueue;
		System.out.println("Acquired Room.");
		int size = queueMembers.size();
		System.out.println("Room size: " + size);
		
//		request.setAttribute("queueMembers", queueMembers);
//		request.setAttribute("queueSize", size);
		
		for(int i = 0; i < size; i++) {
			User u = FirebaseQuery.queryUserID(queueMembers.get(i));
			if(u == null) continue;
			
			out.println("<tr>");
			out.println("<td>" + (i+1) + "</td>");
			out.println("<td>" + u.getUsername() + "</td>");
			out.println("<td>~" + (5*(i+1)) + " mins</td>");
			out.println("</tr>");
		}
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
