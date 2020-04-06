import java.sql.*;

public class validation {
	
	public static boolean Login_check(String email,String pass)
	{
		
	        boolean st =false;
	        try {

	            //loading drivers for mysql
	            Class.forName("com.mysql.jdbc.Driver");

	            //creating connection with the database
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/factory?user=root&password=123456&serverTimezone=UTC");
	            PreparedStatement ps = con.prepareStatement("select * from Accounts where Email='"+email+"'"+ " and Password='"+pass+"'");
	            ResultSet rs =ps.executeQuery();
	            st = rs.next();

	        }
	        catch(Exception e) {
	            e.printStackTrace();
	        }
	        return st;                 
	    
	}
	
	public static boolean regis_check(String email)
	{
		
	        boolean st =false;
	        try {

	            //loading drivers for mysql
	            Class.forName("com.mysql.jdbc.Driver");

	            //creating connection with the database
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/factory?user=root&password=123456&serverTimezone=UTC");
	            PreparedStatement ps = con.prepareStatement("select * from Accounts where Email='"+email+"'");
	            //ps.setString(1, email);
	            ResultSet rs =ps.executeQuery();
	            st = rs.next();

	        }
	        catch(Exception e) {
	            e.printStackTrace();
	        }
	        return !st;                 
	    
	}

}
