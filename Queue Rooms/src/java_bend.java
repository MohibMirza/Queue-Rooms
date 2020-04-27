

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import add_to_firebase_stuff.User;

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
     // Fetch the service account key JSON file contents

        
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
        
        try {
        	String info="{\n" + 
        			"  \"type\": \"service_account\",\n" + 
        			"  \"project_id\": \"cs201-project-c4168\",\n" + 
        			"  \"private_key_id\": \"b660ec6eb897f563218786b6efab0556fa1d2d20\",\n" + 
        			"  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDh86BtLXrt0l6w\\nEdocWSzts5U4ZqmwgbkysBRPRAydRA7PyHJEIBIx+/yBqz75dErNcnPP3Mg843MM\\nHolYIBNYjIDsEBPfBxRrVG8Wx9MJoaoWWQqDsbruFDi8nf8cLcMdBSyuBvaaws7S\\nqFMyZXGCkH2xN29qSBP0WsgXKjE/ACyTqWfPwpaCZHeYBOS25Rnx6igJPWSiMzOY\\nLFVNrWCC0QUNHKPf1bI1R47ue64/Pxf/q87uTfFNwusTwuTd9ezqChc/hTke7A26\\nL7YHNMK22uHP4sEgqnVUhTSB4VH24wI3kYgryCtgetrBgLiLVAUzLuHGki1Nk9hv\\nr1fPMQUZAgMBAAECggEAN+qdMHeZXOJ7SVJZqjtNWZPgIUGSy9oGlWsq04TxJDgz\\n/CrMUFypdBI6pnJVFtG3M0mYLIAkU6EsFvs1nQfvEbJPs1u1NzPe00LlbPtNmCwY\\nVgh6CCZDLuj1M4RcHkKa7wMZUAXsIFeGbG54jFe7dAbC/EjOOzyUJ5wRuV3ZKxAu\\nOA7UY9ukqjiP9RvZE84UZXc0v02VAm9R3HatyUXIinvbyQIs0/Oxt+aeBle/FDaW\\nyllTcjyh+A0+qpQVtHVKnoBKiG6nuYgwjVIv2JQY23Qoiclc8kVxxuhUQm6wSrt0\\nJdqXZ7saA2iVA34Y7qEcuPuKDdLwm2wZN84xUdePYwKBgQD/P0m/ahtagJaxmzOq\\n6O9EZVfnqinqji14EYKoJJq+ml6j5AjDY1JUegPSesOwJ0rSwHi/wZV0hWTe9bdt\\nn8hM9R1IEbenF0NAquSfQb+Jd/ALm0YQu5i3TRDJb9iO/4KjEclrHIkckLQFB32F\\niS4fpUZ732dQ0NY+1ym4Olo1/wKBgQDinjhtJ7TpSG1+TMC2W28MMbLZ0djze+cK\\n+v5XGLYLObj0ltnnOW3NSVvXFL1MH+Yks0jIKtlU5HnhN9EbMfoWX/n/JPHpYMmf\\nmbIITv8eFnw4MDWzI6D0LA6lOV6wRVkEnG347IM2E0T8QVi4dlgyN8aeZ9OXFdKj\\ncFwfxC205wKBgQDpgMpElBsl9hDltafUqHeKyHNceW0TKLIr70jp7WKrD6mYoNnr\\ndQcyRNUOTkSK98cZef583emNySJ4Xa1KWivxSyNYdOzfrQZXOH8huhIw/knZNFkq\\noZOZzKSYwvsIBnNKHtF2bYp5WV92Yl3QNai+RoJue/eWFRaKMmf2I1u3nQKBgFt7\\nM5wc41MpAcpygXkcAAAUL/buV34zDlnIaNQQDa0KaDeUjfJhpImyi3iPzrrkRaub\\nFE55vbIfaXQZbDhmPGa759evulhT0hCzosLn4OTsfsjP7V2bjGYFQwQ/3JGb7QuH\\nen16PJ2fucF7P613WLMtAOkBUNE05gtEWGwabbkRAoGAFOLFZnYuX5aj8NWXLhFF\\nFZh5wVsl6B3uRZDOFmV2X/MDAGep2bubURQVVOsOFNndSMpYCHjrpmT8xj6m8XNJ\\nWu6nxnbzzuq3po1YxwzwQgflp7YxG9d7QpMgT2QVHtf6bhdcWIGFMga9krZ4CkYn\\n3Cw93gYeOR/cVnFpGo7xb9A=\\n-----END PRIVATE KEY-----\\n\",\n" + 
        			"  \"client_email\": \"firebase-adminsdk-qfw6t@cs201-project-c4168.iam.gserviceaccount.com\",\n" + 
        			"  \"client_id\": \"107403988826608138469\",\n" + 
        			"  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" + 
        			"  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" + 
        			"  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" + 
        			"  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-qfw6t%40cs201-project-c4168.iam.gserviceaccount.com\"\n" + 
        			"}";
			
			
            InputStream serviceAccount = new ByteArrayInputStream(info.getBytes(Charset.forName("UTF-8")));

        // Initialize the app with a service account, granting admin privileges
        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://cs201-project-c4168.firebaseio.com/")
            .build();
        FirebaseApp.initializeApp(options);

        // As an admin, the app has access to read and write all data, regardless of Security Rules
        DatabaseReference ref = FirebaseDatabase.getInstance()
            .getReference("restricted_access/secret_document");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
            Object document = dataSnapshot.getValue();
            System.out.println(document);
          }

          @Override
          public void onCancelled(DatabaseError error) {
          }
        });
        }catch(Exception e)
        {
        	System.out.println("Some error!");	
        } 
        		
        try {
            
        	 if(!validation.regis_check(regis_email))
             {
             	out.println("Email-ID already in use");
             	RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
	            rs.include(request, response);
             }
        	 else
        	 {
        		 
        	  String userid_pass= userid + " password=" + regis_pass;
        	  
        		 
        		 
        		 CreateRequest req = new CreateRequest()
     				    .setEmail(regis_email)
     				    .setPassword(regis_pass)
     				    .setDisplayName(userid_pass);
     				    
     				UserRecord userRecord;
     				try {
    					userRecord = FirebaseAuth.getInstance().createUser(req);
    					System.out.println("Successfully created new user: " + userRecord.getUid());
    					out.println("You are sucessfully registered");
    		 
    		            User.createUser(userid, (String)userRecord.getUid());
    		            
    		            request.setAttribute("UserID", (String)userRecord.getUid());
    	            	RequestDispatcher rs = request.getRequestDispatcher("homepage.jsp");
    	                rs.forward(request, response);
    				} catch (FirebaseAuthException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
     				
        	 }
        
        
        }
        catch(Exception se) {
            se.printStackTrace();
        }
		
		
		//doGet(request, response);
	}

}
