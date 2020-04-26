package usc.edu.eq;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

public class Alert {

	//TIMESTAMP
	public String sender;
	public List<String> recipients;
	public String message;
	public String id;
	
	//CREATE AN ALERT AND SEND IT TO THE DATABASE
	public Alert() {
		recipients = new ArrayList<String>();
	}
	
	public Alert(String thesender, List<String> therecipients, String themessage) {
		sender = thesender;
		recipients = therecipients;
		message = themessage;
		id = UUID.randomUUID().toString();
		
		FirebaseQuery.updateAlert(this);
	}
	
	//FIND ALL ALERTS FOR A USER, GIVEN THE USERID
	public static List<Alert> findAlerts(String userid) {
		return FirebaseQuery.queryAlerts(userid);
	}
	
	//MARKS AN ALERT AS "READ" AND UPDATES THE RECIPIENT LIST IN FIREBASE
	public void read(String userid) {
		for(int i = 0; i < recipients.size(); i++) {
			if(recipients.get(i) == userid) {
				recipients.remove(i);
			}
		}
		if(recipients.size() == 0) {
			FirebaseQuery.removeAlert(this);
		}
		else {
			FirebaseQuery.updateAlert(this);
		}
	}
	
	/*
	 * 
	 * EVERYTHING AFTER THIS IS USED FOR DATABASE, DON'T REALLY NEED TO USE THIS STUFF IF YOU DON'T WANT TO
	 * 
	 * 
	 */
	
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public List<String> getRecipients() {
		// TODO Auto-generated method stub
		return recipients;
	}
	
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
		//FirebaseQuery.updateAlert(this);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		FirebaseQuery.updateAlert(this);
	}

	public void setId(String id) {
		this.id = id;
		//FirebaseQuery.updateAlert(this);
	}

	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
		//FirebaseQuery.updateAlert(this);
	}
	
	public static void main(String[] args) {
		List<String> myrecipients = new ArrayList<String>();
		myrecipients.add("I9dH5BF59AfcO5SPtZHCy2VhAwA3");
		Alert test = new Alert("2286", myrecipients, "genisis");
		try {
			List<Alert> test2 = Alert.findAlerts("I9dH5BF59AfcO5SPtZHCy2VhAwA3");
			Thread.sleep(5000);
			System.out.println("HERE: " + test2.size());
			for(Alert one: test2) {
				System.out.println(one.getId());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
