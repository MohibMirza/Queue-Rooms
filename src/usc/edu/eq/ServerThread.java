package usc.edu.eq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import usc.edu.eq.room.AssistanceRoom;
import usc.edu.eq.room.Room;
import usc.edu.eq.user.User;

public class ServerThread extends Thread{
	
	private PrintWriter pw;
	private BufferedReader br;
	private Server server;
	private boolean loggedIn;
	private Room room;
	private User user;
	
	public ServerThread(Socket s, Server server){
		try{
			pw = new PrintWriter(s.getOutputStream());
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			this.server = server;
			loggedIn = false;
			this.start();
		}catch(IOException ioe){
			System.out.println("IOE in Server Thread Constructor: " + ioe.getMessage());
		}
	}
	
	public void sendMessage(String msg){
		pw.println(msg);
		pw.flush();
	}
	
	public void run(){
		try{
			while(true){ // constantly listening to the client
				String msg = br.readLine();
				if(msg == null){
					System.out.println("A user has disconnected.");
					server.serverThreads.remove(this);
					break;
				}
				String arg0 = "", arg1 = "", arg2 = "";
				Scanner scan = new Scanner(msg);
				if(scan.hasNext()){
					arg0 = scan.next();
				}
				if(scan.hasNext()){
					arg1 = scan.next();
				}
				if(scan.hasNext()){
					arg2 = scan.next();
				}
				msg = msg.trim();
				
				if(msg.contains("/create")){ // this all needs to be in a new thread called CommandHandler
					AssistanceRoom r = new AssistanceRoom(arg1);
					server.addRoom(r);
					sendMessage("You have created a new room! Access it with code: " +  r.getHash() + "!");
					server.write("New room created with hash " + r.getHash() + " by " + arg2);
				}else if(msg.contains("/join")){
					// FORWARD WHOLE ROOM OBJECT TO CLIENT AND KEEP SENDING UPDATES UNTIL THEY LEAVE
					if(server.roomExists(Integer.parseInt(arg1))){
						room = server.getRoom(Integer.parseInt(arg1));
						sendMessage("Joined room #" + room.getHash());
						server.write("User has joined room #" + room.getHash());
					}else{
						sendMessage("That room code doesn't exist!");
						server.write("User entered invalid code: " + arg1);
					}
				}else if(msg.contains("/leave")){
					// STOP SENDING ROOM UPDATES
					sendMessage("You have left room #" + room.getHash());
					server.write("User has left room #" + room.getHash());
					room = null;
					
				}else if(msg.contains("/enqueue")){
					if(room != null){
						AssistanceRoom r = (AssistanceRoom) room;
						r.enqueue(arg1);
					}
					
				}else if(msg.contains("/dequeue")){
					if(room != null){
						AssistanceRoom r = (AssistanceRoom) room;
						r.dequeue(arg1);
					}
				}else if(msg.contains("/clearqueue")){
					
				}else if(msg.contains("/requeue")){
					
				}else if(msg.contains("/login")){
					
				}else if(msg.contains("/logout")){
					loggedIn = false;
					
				}else if(msg.contains("/signup")){
					
				}else{
					System.out.println("Invalid msg recieved: " + msg);
				}
				
				server.broadcast(msg, this);
			}
		}catch(IOException ioe){
			System.out.println("IOE in ServerThread.run()");
			// when a client disconnects this exception occurs,
			// create a method in QueueRoom that handles disconnected
			// clients and will remove them from serverThreads vector
		}
	}
}

// 1. We want to create a packet that will send all data to user of the room that he is within
// this will update periodically when change occurs and update his UI panels with data within them.
// 2. To maintain the hashcodes, have a set of all possible hashcodes, select randomly from it and assign
// then remove it from the set, once the room deleter deletes the room, reenter the value back into the set.
// This way you can add more possible values into the set if many are taken.
// 3. Add a close() function that will close off the pw and br and socket so it doesn't cause memory leaks.
