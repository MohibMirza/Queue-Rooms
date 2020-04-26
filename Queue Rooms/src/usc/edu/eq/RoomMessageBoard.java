package usc.edu.eq;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoomMessageBoard {
	
	public List<String> messages;
	public String id;
	
	public RoomMessageBoard() {
		messages = new ArrayList<String>();
		id = UUID.randomUUID().toString();
		
		FirebaseQuery.updateMessageBoard(this);
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void removeMessage(int num) {
		messages.remove(num);
		FirebaseQuery.updateMessageBoard(this);
	}
	
	public static void removeMessage(String id, int num) {
		RoomMessageBoard find = FirebaseQuery.queryMessageBoard(id);
		find.removeMessage(num);
	}
	
	public void addMessage(String message) {
		messages.add(message);
		FirebaseQuery.updateMessageBoard(this);
	}
	
	public static void addMessage(String id, String message) {
		RoomMessageBoard find = FirebaseQuery.queryMessageBoard(id);
		find.addMessage(message);
	}
	
	public static void remove(String id) {
		RoomMessageBoard find = FirebaseQuery.queryMessageBoard(id);
		FirebaseQuery.removeMessageBoard(find);
	}
	
	public static RoomMessageBoard findMessageBoard(String id) {
		return FirebaseQuery.queryMessageBoard(id);
	}

}
