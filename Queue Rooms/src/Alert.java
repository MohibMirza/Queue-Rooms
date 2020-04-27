

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Alert {
	public enum AlertType {
		PRIMARY,
		SUCCESS,
		WARNING
	}
	
	public Date alertTime;
	
	public String alertMessage;
	public AlertType alertType;
	public String alertID;
	public String userID;
	
	public boolean beenRead;
	
	public Alert() {
		
	}
	
	public Alert(Date alertTime, String alertMessage, int alertType, String userID) {
		this.alertTime = alertTime;
		this.alertMessage = alertMessage;
		//this.alertType = alertType;
		this.userID = userID;
		
		this.beenRead = false;
	}
	
	public String getID() {
		return this.alertID;
	}
	
	public Map<String, Object> toMap() { 
		Map<String, Object> dateSerialized = new HashMap<String, Object>();
		dateSerialized.put("alertTime", alertTime);
		dateSerialized.put("alertMessage", alertMessage);
		dateSerialized.put("alertType", alertType);
		
		return dateSerialized;
	}
	
}
