package src;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

@WebServlet("/TopbarUpdate")
public class TopbarUpdate extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter(); // THINGS TO NOTE
		
		System.out.println("Attempting to update topbar.");

		HttpSession session=request.getSession();
        String userid = (String) session.getAttribute("UserID");
		
		//String userid = "I9dH5BF59AfcO5SPtZHCy2VhAwA3";
		
		
        List<Alert> userAlerts = new ArrayList<Alert>();
        userAlerts= Alert.findAlerts(userid);
        try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        out.println("<a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"alertsDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n" + 
        		"          <i class=\"fas fa-bell fa-fw\"></i>\r\n" + 
        		"          <span class=\"badge badge-danger badge-counter\">" + userAlerts.size() + "</span>\r\n" + 
        		"          </a>\r\n" + 
        		"          <div class=\"dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in\" aria-labelledby=\"alertsDropdown\">\r\n" + 
        		"            <div id=\"TopbarDisplay\">");
        
        out.println("<h6 class=\"dropdown-header\">Alerts Center</h6>");
        
        for (int i=0; i<userAlerts.size(); i++) {       	
        
        	out.println("<a class=\"dropdown-item d-flex align-items-center\" href=\"#\"><div class=\"mr-3\"><div class=\"icon-circle bg-primary\"><i class=\"fas fa-file-alt text-white\"></i></div></div>");
        
        	out.println("<div><div class=\"small text-gray-500\">" + userAlerts.get(i).sender + "</div>");
        	
        	out.println("<span class=\"font-weight-bold\">" + userAlerts.get(i).message + "</span></div></a>");
        }
        
        out.println("<a class=\"dropdown-item text-center small text-gray-500\" href=\"#\">Show All Alerts</a>\r\n" + 
        		"              </div>");

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
