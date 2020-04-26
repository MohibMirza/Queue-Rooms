package usc.edu.eq.room;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Room {
	
	private int hash;
	private String owner;
	private Set<String> admins;
	private Set<String> visitedUsers;
	
	Room(String owner){
		this.owner = owner;
		generateHash();
		admins = Collections.synchronizedSet(new HashSet<String>());
		visitedUsers = Collections.synchronizedSet(new HashSet<String>());
	}
	
	private void generateHash(){
		// GENERATE A UNIQUE 5 CHAR ROOM CODE HERE
		// USING RANDOM HASH FOR NOW! 
		hash = ((int) (Math.random()*100000.0));
	}
	
	public void setOwner(String owner){
		this.owner = owner;
	}
	
	public void setAdmin(String admin){
		if(!admins.contains(admin)){
			admins.add(admin);			
		}
	}
	
	public void removeAdmin(String admin){
		admins.remove(admin);
	}
	
	public int getHash(){
		return hash;
	}
	
}