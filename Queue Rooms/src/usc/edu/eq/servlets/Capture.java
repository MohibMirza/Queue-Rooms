package usc.edu.eq.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Capture
 */
@WebServlet("/Capture")
public class Capture extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 private static String ACCESS_KEY = "c452c24fe1e846ebb1b80d59c697eb21";
    private static String APIFLASH_ENDPOINT = "https://api.apiflash.com/v1/urltoimage";
    private static String screenshotRequestURL;
    
    private static String screenshotSrc = "screenshot.jpg";
    public static String screenshotResponseURL = "https://www.google.com/url?sa=i&url=https%3A%2F%2Ftwitter.com%2Fanimalcrossing&psig=AOvVaw0SNuxoOP2rW1LXy-RMJM7j&ust=1587959628151000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCLjzg-GYhekCFQAAAAAdAAAAABAD";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Capture() {
        super();
    	System.out.println("in constructor of Capture servlet");
    }
    
    public static String getScreenshotSrc() {
    	String src = screenshotSrc;
    	return screenshotSrc;
    }
    
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		screenshotRequestURL = request.getParameter("screenshotRequestURL");
		System.out.println(screenshotRequestURL);
		PrintWriter pw = response.getWriter();
    	
    	 URL url = new URL(String.format("%s?access_key=%s&url=https://" + screenshotRequestURL, APIFLASH_ENDPOINT, ACCESS_KEY));
    	 screenshotResponseURL = url.toString();
    	 pw.print(screenshotResponseURL);
    	 
    	 System.out.println(url);
        
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
		doGet(request, response);
	}

}
