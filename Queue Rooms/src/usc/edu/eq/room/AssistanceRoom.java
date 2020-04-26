package usc.edu.eq.room;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class AssistanceRoom extends Room{

	private BlockingQueue<String> queue;
	
	public AssistanceRoom(String owner) {
		super(owner);
		queue = new LinkedBlockingQueue<String>();
	}
	
	public void enqueue(String user){
		if(!queue.contains(user)){
			queue.add(user);
		}
	}
	
	public void dequeue(String user){
		if(queue.contains(user)){
			queue.remove(user);
		}
	}

}
