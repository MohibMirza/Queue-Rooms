import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

public class Alert {

	public String sender;
	public List<String> recipients;
	public String message;
	public String id;
	
	//CREATE AN ALERT AND SEND IT TO THE DATABASE
	public Alert() {
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
		System.out.println(this.id);
		System.out.println(this.recipients.toString());
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
		FirebaseQuery.updateAlert(this);
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
		FirebaseQuery.updateAlert(this);
	}

	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
		FirebaseQuery.updateAlert(this);
	}
	
	public static void main(String[] args) {
		List<String> myrecipients = new ArrayList<String>();
		myrecipients.add("2102");
		myrecipients.add("1477");
		myrecipients.add("3854");
		Alert test = new Alert("2286", myrecipients, "test message");
		try {
			List<Alert> test2 = Alert.findAlerts("2102");
			Thread.sleep(10000);
			System.out.println("HERE: " + test2.toString());
			System.out.println(test2.get(0).getId());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
