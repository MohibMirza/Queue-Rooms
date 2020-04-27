package b_end;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
public class fb_info_json {
	public fb_info call_me(String access_token) throws Exception {
	     String url = "https://graph.facebook.com/v2.12/me?fields=id,name,picture,email&access_token="+access_token;
	     URL obj = new URL(url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     // optional default is GET
	     con.setRequestMethod("GET");
	     //add request header
	     con.setRequestProperty("User-Agent", "Mozilla/5.0");
	     int responseCode = con.getResponseCode();
	     System.out.println("\nSending 'GET' request to URL : " + url);
	     System.out.println("Response Code : " + responseCode);
	     BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     in.close();
	     System.out.println(response);
	     fb_info obj_Profile=new fb_info();
	     JSONObject myResponse = new JSONObject(response.toString());
	     obj_Profile.setUser_name(myResponse.getString("name"));
	     obj_Profile.setId(myResponse.getString("id"));
	     obj_Profile.setEmail(myResponse.getString("email"));
	     JSONObject picture_reponse=myResponse.getJSONObject("picture");
	     JSONObject data_response=picture_reponse.getJSONObject("data");
	     System.out.println("URL : "+data_response.getString("url"));
	     obj_Profile.setProfile_picture(data_response.getString("url"));
		return obj_Profile;
	   }
}
