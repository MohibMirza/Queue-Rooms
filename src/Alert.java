package queuerooms2;

import java.util.Date;

public class Alert {
	public Date alertTime;
	
	public String alertMessage;
	public int alertType;
	
	public Alert() {
		
	}
	
	public Alert(Date alertTime, String alertMessage, int alertType) {
		this.alertTime = alertTime;
		this.alertMessage = alertMessage;
		this.alertType = alertType;
	}
	
}
