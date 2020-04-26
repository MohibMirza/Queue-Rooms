package usc.edu.eq;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

import com.google.api.core.ApiFuture;
//import org.slf4j.impl.StaticLoggerBinder;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import usc.edu.eq.room.Room;

public class FirebaseQuery {
	
	final public static String PATH = "./serviceAccount.json";
	
	public static void setValueTest() {
		System.out.println("test");
		String key = PATH;
		String databaseURL = "https://cs201-project-c4168.firebaseio.com";
		
		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream(key);
			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					  .setDatabaseUrl(databaseURL)
					  .build();

			FirebaseApp.initializeApp(options);
			
			FirebaseDatabase myFirebase = FirebaseDatabase.getInstance();
			DatabaseReference ref = myFirebase.getReference();
			System.out.println(ref.toString());
			Map<String, String> users = new HashMap<>();
			users.put("alanisawesome", "Alan Turing");
			users.put("gracehop", "Grace Hopper");

			final CountDownLatch latch = new CountDownLatch(1);
	        ref.child("test").setValue(users, new DatabaseReference.CompletionListener() {
	            @Override
	            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
	                if (databaseError != null) {
	                    System.out.println("Data could not be saved " + databaseError.getMessage());
	                    latch.countDown();
	                } else {
	                    System.out.println("Data saved successfully.");
	                    latch.countDown();
	                }
	            }
	        });
	        latch.await();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}

		
		System.out.println("made it");
		//ref.child(add.getID()).updateChildrenAsync(add);
	}
	
	
	public static void setValueAsyncTest() {
		System.out.println("test");
		String key = PATH;
		String databaseURL = "https://cs201-project-c4168.firebaseio.com";
		
		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream(key);
			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					  .setDatabaseUrl(databaseURL)
					  .build();

			FirebaseApp.initializeApp(options);
			
			FirebaseDatabase myFirebase = FirebaseDatabase.getInstance();
			DatabaseReference ref = myFirebase.getReference();
			System.out.println(ref.toString());
			Map<String, Object> users = new HashMap<>();
			users.put("alanisawesome", "AlanTuring");


			ApiFuture<Void> watch = ref.child("test2").updateChildrenAsync(users);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}

		
		System.out.println("made it");
		//ref.child(add.getID()).updateChildrenAsync(add);
	}
	
	public static void readTest() {
		String key = PATH;
		String databaseURL = "https://cs201-project-c4168.firebaseio.com";
		
		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream(key);
			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					  .setDatabaseUrl(databaseURL)
					  .build();

			FirebaseApp.initializeApp(options);
			
		}
		catch(Exception e) {
			//e.printStackTrace();
		}
		FirebaseDatabase myFirebase = FirebaseDatabase.getInstance();
		DatabaseReference ref = myFirebase.getReference("test2");

		ref.addChildEventListener(new ChildEventListener() {
			  @Override
			  public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
			    String data = dataSnapshot.getValue(String.class);
			    System.out.println(dataSnapshot.getKey() + " is " + data);
			  }

			@Override
			public void onCancelled(DatabaseError arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onChildChanged(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onChildMoved(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onChildRemoved(DataSnapshot arg0) {
				// TODO Auto-generated method stub
				
			}

			  // ...
			});
	}
	
	public static void deleteTest() {
		System.out.println("test");
		String key = PATH;
		String databaseURL = "https://cs201-project-c4168.firebaseio.com";
		
		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream(key);
			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					  .setDatabaseUrl(databaseURL)
					  .build();

			FirebaseApp.initializeApp(options);
			
			FirebaseDatabase myFirebase = FirebaseDatabase.getInstance();
			DatabaseReference ref = myFirebase.getReference();
			System.out.println(ref.toString());
			Map<String, Object> users = new HashMap<>();
			users.put("alanisawesome", null);


			ApiFuture<Void> watch = ref.child("test2").updateChildrenAsync(users);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}

		
		System.out.println("made it");
		//ref.child(add.getID()).updateChildrenAsync(add);
	}

	public static void updateAlert(Alert add) {
		String key = PATH;
		String databaseURL = "https://cs201-project-c4168.firebaseio.com";
		
		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream(key);
			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					  .setDatabaseUrl(databaseURL)
					  .build();

			FirebaseApp.initializeApp(options);
		}
		catch(Exception e) {
		}
		FirebaseDatabase myFirebase = FirebaseDatabase.getInstance();
		DatabaseReference ref = myFirebase.getReference("Alerts");

		Map<String, Object> update = new HashMap<>();
		update.put(add.getId(), add);
		ref.updateChildrenAsync(update);
	}
	
	public static void removeAlert(Alert remove) {
		String key = PATH;
		String databaseURL = "https://cs201-project-c4168.firebaseio.com";
		
		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream(key);
			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					  .setDatabaseUrl(databaseURL)
					  .build();

			FirebaseApp.initializeApp(options);
		}
		catch(Exception e) {
		}
		FirebaseDatabase myFirebase = FirebaseDatabase.getInstance();
		DatabaseReference ref = myFirebase.getReference("Alerts");
		Map<String, Object> update = new HashMap<>();
		update.put(remove.getId(), null);
		ref.updateChildrenAsync(update);
	}
	
	public static void updateRecipients(Alert update) {
		String key = PATH;
		String databaseURL = "https://cs201-project-c4168.firebaseio.com";
		
		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream(key);
			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					  .setDatabaseUrl(databaseURL)
					  .build();

			FirebaseApp.initializeApp(options);
		}
		catch(Exception e) {
			////e.printStackTrace();
		}
		
		FirebaseDatabase myFirebase = FirebaseDatabase.getInstance();
		DatabaseReference ref = myFirebase.getReference("Alerts");

		DatabaseReference upvotesRef = ref.child(update.getId()).child("recipients");
		GenericTypeIndicator<Vector<String>> t =
		         new GenericTypeIndicator<Vector<String>>() {};
		upvotesRef.runTransaction(new Transaction.Handler() {
		  @Override
		  public Transaction.Result doTransaction(MutableData mutableData) {
		    Vector<String> currentValue = mutableData.getValue(t);
		    System.out.println("Firebase: " + currentValue.toString());
		    if (currentValue.size() == 0) {
		      removeAlert(update);
		    } else {
		      mutableData.setValue(update.getRecipients());
		    }

		    return Transaction.success(mutableData);
		  }

		  @Override
		  public void onComplete(
		      DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
		  }
		});
	}
	
	//adds or updates existing Room in database
	public static void updateRoom(Room add) {
		String key = PATH;
		String databaseURL = "https://cs201-project-c4168.firebaseio.com";
		
		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream(key);
			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					  .setDatabaseUrl(databaseURL)
					  .build();

			FirebaseApp.initializeApp(options);
		}
		catch(Exception e) {
			////e.printStackTrace();
		}
		FirebaseDatabase myFirebase = FirebaseDatabase.getInstance();
		DatabaseReference ref = myFirebase.getReference("Rooms");

		Map<String, Object> update = new HashMap<>();
		update.put(add.getId(), add);
		ref.updateChildrenAsync(update);
	}

	public static void deleteRoom(Room remove) {
		String key = PATH;
		String databaseURL = "https://cs201-project-c4168.firebaseio.com";
		
		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream(key);
			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					  .setDatabaseUrl(databaseURL)
					  .build();

			FirebaseApp.initializeApp(options);
		}
		catch(Exception e) {
			//e.printStackTrace();
		}
		FirebaseDatabase myFirebase = FirebaseDatabase.getInstance();
		DatabaseReference ref = myFirebase.getReference("Rooms");

		Map<String, Object> update = new HashMap<>();
		update.put(remove.getId(), null);
		ref.updateChildrenAsync(update);

	}
	
	//adds or updates existing User to Database
	public static void updateUser(User add) {
		String key = PATH;
		String databaseURL = "https://cs201-project-c4168.firebaseio.com";
		
		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream(key);
			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					  .setDatabaseUrl(databaseURL)
					  .build();

			FirebaseApp.initializeApp(options);
		}
		catch(IllegalStateException ISE) {
			
		}
		catch(Exception e) {
			//e.printStackTrace();
		}
		
		FirebaseDatabase myFirebase = FirebaseDatabase.getInstance();
		DatabaseReference ref = myFirebase.getReference("Users");

		Map<String, Object> update = new HashMap<>();
		String test = add.getUserid();
		update.put(test, add);
		ref.updateChildrenAsync(update);
	}
	
//	public static void addQueue(String newUser, String roomid) {
//		Room find = FirebaseQuery.queryRoomID(roomid);
//		find.mainQueue.add(newUser);
//		FirebaseQuery.updateRoom(find);
//	}

	public static Vector<Alert> queryAlerts(String userid){
		
		String key = PATH;
		String databaseURL = "https://cs201-project-c4168.firebaseio.com";
		Vector<Alert> output = new Vector<Alert>();
		Semaphore semaphore = new Semaphore(0);
		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream(key);
			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					  .setDatabaseUrl(databaseURL)
					  .build();

			FirebaseApp.initializeApp(options);
			
		}
		catch(Exception e) {
			//e.printStackTrace();
		}
		FirebaseDatabase myFirebase = FirebaseDatabase.getInstance();
		DatabaseReference ref = myFirebase.getReference("Alerts");

		ValueEventListener pls =  ref.addValueEventListener(new ValueEventListener() {

			@Override
			public void onCancelled(DatabaseError snapshot) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onDataChange(DataSnapshot snapshot) {
				// TODO Auto-generated method stub
				System.out.println(snapshot.getRef());
				for(DataSnapshot child: snapshot.getChildren()) {
					Alert data = child.getValue(Alert.class);
				    List<String> users = data.getRecipients();
					for(int i = 0; i < users.size(); i++){
						if(users.get(i).equals(userid)){
							output.add(data);
						}
					}
				}
				System.out.println("Releasing Semaphores");
				semaphore.release();
			}

			});
		
		try {
			semaphore.acquire();
			ref.removeEventListener(pls);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
	

	public static Vector<Room> queryRooms(String userid){
		
		String key = PATH;
		String databaseURL = "https://cs201-project-c4168.firebaseio.com";
		final Semaphore semaphore = new Semaphore(0);
		FileInputStream serviceAccount = null;
		Vector<Room> output = new Vector<Room>();
		try {
			serviceAccount = new FileInputStream(key);
			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					  .setDatabaseUrl(databaseURL)
					  .build();

			FirebaseApp.initializeApp(options);
			
		}
		catch(Exception e) {
			//e.printStackTrace();
		}
		
		FirebaseDatabase myFirebase = FirebaseDatabase.getInstance();
		DatabaseReference ref = myFirebase.getReference("Rooms");

		ValueEventListener pls =  ref.addValueEventListener(new ValueEventListener() {

			@Override
			public void onCancelled(DatabaseError snapshot) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onDataChange(DataSnapshot snapshot) {
				// TODO Auto-generated method stub
				System.out.println(snapshot.getRef());
				for(DataSnapshot child: snapshot.getChildren()) {
					Room data = child.getValue(Room.class);
				    List<String> users = data.getUsers();
					for(int i = 0; i < users.size(); i++){
						if(users.get(i).equals(userid)){
							output.add(data);
						}
					}
				}
				System.out.println("Releasing Semaphores");
				semaphore.release();
			}

			});
		
		try {
			semaphore.acquire();
			ref.removeEventListener(pls);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
	
	public static User queryUserName(String name) {
		String key = PATH;
		String databaseURL = "https://cs201-project-c4168.firebaseio.com";
		Semaphore semaphore = new Semaphore(0);
		Vector<User> output = new Vector<User>();
		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream(key);
			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					  .setDatabaseUrl(databaseURL)
					  .build();

			FirebaseApp.initializeApp(options);
			
		}
		catch(Exception e) {
			//e.printStackTrace();
		}
		
		FirebaseDatabase myFirebase = FirebaseDatabase.getInstance();
		DatabaseReference ref = myFirebase.getReference("Users");
		
		
		ChildEventListener test = ref.addChildEventListener(new ChildEventListener() {
			  @Override
			  public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
			    User data = dataSnapshot.getValue(User.class);
			    if(data.getUsername().equals(name)){
					output.add(data);
				}
			    System.out.println("Releasing semaphore");
			    semaphore.release();
			  }

			@Override
			public void onCancelled(DatabaseError arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onChildChanged(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onChildMoved(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onChildRemoved(DataSnapshot arg0) {
				// TODO Auto-generated method stub
				
			}

			});
		try {
			semaphore.acquire();
			System.out.println("Acquired Semaphore");
			return output.get(0);
		}
		catch(Exception e) {
		}
		return null;
	}

	public static User queryUserID(String id) {
		String key = PATH;
		String databaseURL = "https://cs201-project-c4168.firebaseio.com";
		List<User> output = new ArrayList<User>();
		final Semaphore semaphore = new Semaphore(0);
		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream(key);
			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					  .setDatabaseUrl(databaseURL)
					  .build();

			FirebaseApp.initializeApp(options);
			
		}
		catch(Exception e) {
			//e.printStackTrace();
		}
		FirebaseDatabase myFirebase = FirebaseDatabase.getInstance();
		DatabaseReference ref = myFirebase.getReference("Users");
		ValueEventListener once = ref.addValueEventListener(new ValueEventListener() {
			  @Override
			public void onDataChange(DataSnapshot snapshot) {
			    if (snapshot.hasChild(id)) {
			      output.add(snapshot.child(id).getValue(User.class));
			    }
			    System.out.println("releasing sempahore");
			    semaphore.release();
			  }

			@Override
			public void onCancelled(DatabaseError arg0) {
				// TODO Auto-generated method stub
				
			}
			});
		
		try {
			semaphore.acquire();
			ref.removeEventListener(once);
			System.out.println("Semaphore acquired");
			if(output.size() >= 1)
				return output.get(0);
		}
		catch(Exception e) {
		}
		
		return null;
		
	}
	
	public static Room queryRoomID(String id) {
		String key = PATH;
		String databaseURL = "https://cs201-project-c4168.firebaseio.com";
		List<Room> output = new ArrayList<Room>();
		final Semaphore semaphore = new Semaphore(0);
		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream(key);
			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					  .setDatabaseUrl(databaseURL)
					  .build();

			FirebaseApp.initializeApp(options);

		}
		catch(Exception e) {
			//e.printStackTrace();
		}
		FirebaseDatabase myFirebase = FirebaseDatabase.getInstance();
		DatabaseReference ref = myFirebase.getReference("Rooms");
		ValueEventListener once = ref.addValueEventListener(new ValueEventListener() {
			  @Override
			public void onDataChange(DataSnapshot snapshot) {
			    if (snapshot.hasChild(id)) {
			      output.add(snapshot.child(id).getValue(Room.class));
			    }
			    System.out.println("releasing sempahore");
			    semaphore.release();
			  }

			@Override
			public void onCancelled(DatabaseError arg0) {
				// TODO Auto-generated method stub

			}
			});

		try {
			semaphore.acquire();
			ref.removeEventListener(once);
			System.out.println("Semaphore acquired");
			if(output.size() >= 1)
				return output.get(0);
		}
		catch(Exception e) {
		}

		return null;

	}

	
	public static void main(String args[]) {
		try {
			//setValueAsyncTest();
			//readTest();
			//Room test = new Room("owner", "testroom", "description", "earth");
			//test.addUser("I9dH5BF59AfcO5SPtZHCy2VhAwA3");
			// List<Room> test2 = Room.findRooms("I9dH5BF59AfcO5SPtZHCy2VhAwA3");
			// List<Room> test3 = Room.findRooms("I9dH5BF59AfcO5SPtZHCy2VhAwA3");
			// Thread.sleep(5000);
			// System.out.println(test2.size());
			// System.out.println(test3.size());
			// System.out.println("Done");
			Room room = queryRoomID("ac2ac6e8-6158-4de0-94d6-1d3263d0a631");
			System.out.println(room.getLocation());
			
			LinkedList<String> queueMembers = room.getMainQueue();
			System.out.println(queueMembers.size());
			room.addQueue("NOT-A-REAL-ID");
			System.out.println(queueMembers.size());
			
			LinkedList<String> queueMembers2 = room.getMainQueue();
			System.out.println(queueMembers2.size());
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}