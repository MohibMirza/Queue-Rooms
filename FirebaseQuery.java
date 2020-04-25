import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

public class FirebaseQuery {
	
	final public static String PATH = "C:\\Users\\Andrew Zhou\\Downloads\\cs201-project-c4168-firebase-adminsdk-qfw6t-ea581bc0e3.json";
	
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
	
	public static Vector<Alert> queryAlerts(String userid){
		
		String key = PATH;
		String databaseURL = "https://cs201-project-c4168.firebaseio.com";
		Vector<Alert> output = new Vector<Alert>();
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

		ref.addChildEventListener(new ChildEventListener() {
			  @Override
			  public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
			    Alert data = dataSnapshot.getValue(Alert.class);
			    List<String> recipients = data.getRecipients();
			    for(int i = 0; i < recipients.size(); i++) {
			    	if(recipients.get(i).equals(userid)) {
			    		output.add(data);
			    	}
			    }
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
		return output;
	}
	

	public static Vector<Room> queryRooms(String userid){
		
		String key = PATH;
		String databaseURL = "https://cs201-project-c4168.firebaseio.com";
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
		
		ref.addChildEventListener(new ChildEventListener() {
			  @Override
			  public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
			    Room data = dataSnapshot.getValue(Room.class);
			    List<String> users = data.getUsers();
				for(int i = 0; i < users.size(); i++){
					if(users.get(i).equals(userid)){
						output.add(data);
					}
				}
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
		
		
		ref.addChildEventListener(new ChildEventListener() {
			  @Override
			  public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
			    User data = dataSnapshot.getValue(User.class);
			    if(data.getUsername().equals(name)){
					output.add(data);
				}
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
	
	public static void main(String args[]) {
		try {
			//setValueAsyncTest();
			//readTest();
			System.out.println("HERE " + queryUserID("4977").getUsername());
			//addAlert();
			Thread.sleep(5000);
			System.out.println("Done");
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}