package usc.edu.eq;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

import usc.edu.eq.room.AssistanceRoom;
import usc.edu.eq.room.Room;
import usc.edu.eq.room.RoomPool;

public class Server {
	
	public Vector<ServerThread> serverThreads;
	private RoomPool rooms;
	
	public Server(int port){
		try{
			System.out.println("Binding to PORT " + port);
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Bound to PORT " + port);
			serverThreads = new Vector<ServerThread>();
			rooms = new RoomPool("Main");
			while(true){ // listens for new connections to come in
				Socket s = ss.accept();
				System.out.println("Connection from: " + s.getInetAddress());
				ServerThread st = new ServerThread(s, this);
				serverThreads.add(st);
			}
		}catch(IOException ioe){
			System.out.println("IOE in Server Constructor: " + ioe.getMessage());
		}
		
	}
	
	public void broadcast(String message, ServerThread st){
		if(message != null && message.length() > 0){
			System.out.println(message);
			for(ServerThread threads: serverThreads){
				if(st != threads){
					threads.sendMessage(message);
				}
			}
			
		}
	}
	
	public void write(String message){
		System.out.println("SERVER: " + message);
	}

	public void addRoom(Room r){
		rooms.add(r);
	}
	
	public void removeRoom(int hash){
		rooms.remove(hash);
	}
	
	public Room getRoom(int hash){
		return rooms.get(hash);
	}
	
	public boolean roomExists(int hash){
		return rooms.contains(hash);
	}
	
	public void test(){
		System.out.println("test");
	}
	
	public static void main(String[] args) {
		Server q = new Server(1337);
	}
}
