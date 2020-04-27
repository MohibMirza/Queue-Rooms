package usc.edu.eq.room;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RoomPool {
	
	// private Map<Integer, Room> rooms;
	private ConcurrentHashMap<Integer, Room> rooms;
	private String name;
	
	public RoomPool(String name){
		this.name = name;
		rooms = new ConcurrentHashMap<Integer, Room>();
	}
	
	public void add(Room r){
		// GENERATE UNIQUE HASH HERE INSTEAD OF WITHIN ROOM CLASS
		// WOULD BE EASIER TO DETECT COLLISIONS THAT WAY SINCE
		// YOU HAVE ACCESS TO ALL ROOMS WITHIN SET
		rooms.put(r.getHash(), r);
	}
	
	public void remove(int hash){
		rooms.remove(hash);
	}
	
	public Room get(int hash){
		return rooms.get(hash);
	}
	
	public boolean contains(int hash){
		return rooms.containsKey(hash);
	}
	
	public int size(){
		return rooms.size();
	}
	
}
