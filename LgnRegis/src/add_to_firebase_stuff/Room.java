package add_to_firebase_stuff;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.UUID;
import java.util.List;

public class Room {

	public LinkedList<String> mainQueue;
	public List<String> members;
	public List<String> owner;
	public String roomID;
	
	
	//CREATES ROOM AND PUBLISHES IT TO DATABASE
	public Room(String newOwner) {
		owner = new ArrayList<String>();
		owner.add(newOwner);
		mainQueue = new LinkedList<String>();
		members = new ArrayList<String>();
		roomID = UUID.randomUUID().toString();
		
		FirebaseQuery.updateRoom(this);
	}
	
	//FINDS ALL ROOMS CORRESPONDING TO A PERSON
	public static List<Room> findRooms(String userid) {
		return FirebaseQuery.queryRooms(userid);
	}
	
	public List<String> findUsernames(){
		List<String> output = new ArrayList<String>();
		for(int i = 0; i < members.size(); i++) {
			output.add(FirebaseQuery.queryUserID(members.get(i)).getUsername());
		}
		return output;
	}
	
	//FINDS THE CURRENT SIZE OF THE QUEUE
	public int queueSize() {
		return mainQueue.size();
	}
	
	//FINDS PLACE OF INDIVIDUAL IN QUEUE BASED ON USERID -1 MEANS YOU AREN'T IN QUEUE
	public int findPlaceInQueue(String userid) {
		for(int i = 0; i < mainQueue.size(); i++) {
			if(mainQueue.get(i) == userid) {
				return i;
			}
		}
		return -1;
	}
	
	//ADDS A USER TO THE ROOM AND UPDATES DATABASE
	public void addUser(String newUser) {
		members.add(newUser);
		FirebaseQuery.updateRoom(this);
	}
	
	//ADDS MULTIPLE USERS TO ROOM AND UPDATES DATABASE
	public void addUser(List<String> newUser) {
		for(int i = 0; i < newUser.size(); i++) {
			members.add(newUser.get(i));
		}
		FirebaseQuery.updateRoom(this);
	}
	
	//ADDS OWNER TO ROOM AND UPDATES DATABASE
	public void addOwner(String newOwner) {
		owner.add(newOwner);
		FirebaseQuery.updateRoom(this);
	}
	
	//ADDS MULTIPLE OWNERS TO ROOM AND UPDATES DATABASE
	public void addOwner(List<String> newOwner) {
		for(int i = 0; i < newOwner.size(); i++) {
			members.add(newOwner.get(i));
		}
		FirebaseQuery.updateRoom(this);
	}
	
	//ADDS A USER TO THE QUEUE, UPDATES DATABASE
	public void addQueue(String newUser) {
		mainQueue.add(newUser);
		FirebaseQuery.updateRoom(this);
	}
	
	//POPS QUEUE
	public User popQueue() {
		if(!mainQueue.isEmpty()) {
			String output = mainQueue.pop();
			FirebaseQuery.updateRoom(this);
			return FirebaseQuery.queryUserID(output);
		}
		return null;
	}
	
	
	public String getID() {
		return roomID;
	}

	public List<String> getUsers() {
		return members;
	}
	
//	public static void main(String[] args) {
//		for(int i = 0 ; i < 10; i++) {
//			System.out.println(UUID.randomUUID().toString());
//		}
//	}

}
