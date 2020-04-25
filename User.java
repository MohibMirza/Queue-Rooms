import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User {
	
	public String userid;
	public String username;
	public List<String> friends;
	
	public User() {
		friends = new ArrayList<String>();
	}
	
	public User(String theusername, String theuserid) {
		userid = theuserid;
		username = theusername;
		friends = new ArrayList<String>();
		FirebaseQuery.updateUser(this);
	}
	
	//STATIC FUNCTIONS
	public static void createUser(String theusername, String theuserid) {
		User a = new User(theusername, theuserid);
		return;
	}
	
	public static List<Room> findRooms(String userID) {
		return FirebaseQuery.queryRooms(userID);

	}
	
	public static List<String> getFriends(String userid) {
		User currentuser = FirebaseQuery.queryUserID(userid);
		return currentuser.getFriends();
	}
	
	public static void addFriends(String thisid, String targetid) {
		User currentuser = FirebaseQuery.queryUserID(thisid);
		currentuser.addFriend(targetid);
	}
	
	public static User findUserID(String id) {
		return FirebaseQuery.queryUserID(id);
	}
	
	//HERE ENDS THE STATIC FUNCTIONS
	//DO NOT USE, LET ME KNOW IF YOU WANT TO SO I CAN FIX IT
	public static User findUserName(String name) {
		return FirebaseQuery.queryUserName(name);
	}
	
	public void addFriend(String friend) {
		friends.add(friend);
		FirebaseQuery.updateUser(this);
	}
	
	public void addFriend(List<String> friend) {
		for(int i = 0; i < friend.size(); i++) {
			friends.add(friend.get(i));
		}
		FirebaseQuery.updateUser(this);
	}
	
	public String getUserid() {
		// TODO Auto-generated method stub
		return userid;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	
	public List<String> getFriends() {
		return friends;
	}
	

	public void setUserid(String userid) {
		this.userid = userid;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setFriends(List<String> friends) {
		this.friends = friends;
	}
	

}
