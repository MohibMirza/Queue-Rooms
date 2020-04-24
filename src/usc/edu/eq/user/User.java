package usc.edu.eq.user;

import java.util.Vector;

public class User extends Guest{
	
	private String name;
	private String UUID;
	private Vector<Integer> rooms;
	
	public User(String name){
		this.name = name;
		UUID = generateUUID();
	}
	
	private String generateUUID() {
		java.util.UUID uid = java.util.UUID.randomUUID();
		return uid.toString();
	}

	public void addRoom(int hash){
		rooms.add(hash);
	}
	
	public void removeRoom(int hash){
		rooms.remove(hash);
	}
	
	public Vector<Integer> getRooms(){
		return rooms;
	}
	
	public String getUUID(){
		return UUID;
	}
}