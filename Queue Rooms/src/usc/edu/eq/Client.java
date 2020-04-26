package usc.edu.eq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import usc.edu.eq.user.User;

public class Client extends Thread{
	
	private BufferedReader br;
	private PrintWriter pw;
	private static User user;
	
	public Client(String hostname, int port){
		try{
			System.out.println("Try to connect to " + hostname + ":" + port);
			Socket s = new Socket(hostname, port);
			System.out.println("Connected to " + hostname + ":" + port);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(s.getOutputStream());
			this.start();
			Scanner scan = new Scanner(System.in);
			System.out.print("Username: ");
			String name = scan.nextLine();
			user = new User(name);
			
			while(true){
				String line = scan.nextLine();
				pw.println(line + " " + user.getUUID());
				pw.flush();
			}
			
		}catch(IOException ioe){
			System.out.println("IOE in Client Constructor: " + ioe.getMessage());
		}
	}
	
	public void run(){
		try{
			while(true){
				String line = br.readLine();
				if(line == null){
					System.out.println("Connection with server lost!");
					break;
				}
				System.out.println(line);
			}
		}catch(IOException ioe){
			System.out.println("IOE in Client.run(): " + ioe.getMessage());
		}
	}
	
	public static void main(String[] args) {
		Client cc = new Client("localhost", 1337);
	}
}
