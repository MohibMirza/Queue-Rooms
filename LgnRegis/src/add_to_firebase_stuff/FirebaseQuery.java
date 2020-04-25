package add_to_firebase_stuff;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
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
	
	static String info="{\n" + 
			"  \"type\": \"service_account\",\n" + 
			"  \"project_id\": \"cs201-project-c4168\",\n" + 
			"  \"private_key_id\": \"b660ec6eb897f563218786b6efab0556fa1d2d20\",\n" + 
			"  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDh86BtLXrt0l6w\\nEdocWSzts5U4ZqmwgbkysBRPRAydRA7PyHJEIBIx+/yBqz75dErNcnPP3Mg843MM\\nHolYIBNYjIDsEBPfBxRrVG8Wx9MJoaoWWQqDsbruFDi8nf8cLcMdBSyuBvaaws7S\\nqFMyZXGCkH2xN29qSBP0WsgXKjE/ACyTqWfPwpaCZHeYBOS25Rnx6igJPWSiMzOY\\nLFVNrWCC0QUNHKPf1bI1R47ue64/Pxf/q87uTfFNwusTwuTd9ezqChc/hTke7A26\\nL7YHNMK22uHP4sEgqnVUhTSB4VH24wI3kYgryCtgetrBgLiLVAUzLuHGki1Nk9hv\\nr1fPMQUZAgMBAAECggEAN+qdMHeZXOJ7SVJZqjtNWZPgIUGSy9oGlWsq04TxJDgz\\n/CrMUFypdBI6pnJVFtG3M0mYLIAkU6EsFvs1nQfvEbJPs1u1NzPe00LlbPtNmCwY\\nVgh6CCZDLuj1M4RcHkKa7wMZUAXsIFeGbG54jFe7dAbC/EjOOzyUJ5wRuV3ZKxAu\\nOA7UY9ukqjiP9RvZE84UZXc0v02VAm9R3HatyUXIinvbyQIs0/Oxt+aeBle/FDaW\\nyllTcjyh+A0+qpQVtHVKnoBKiG6nuYgwjVIv2JQY23Qoiclc8kVxxuhUQm6wSrt0\\nJdqXZ7saA2iVA34Y7qEcuPuKDdLwm2wZN84xUdePYwKBgQD/P0m/ahtagJaxmzOq\\n6O9EZVfnqinqji14EYKoJJq+ml6j5AjDY1JUegPSesOwJ0rSwHi/wZV0hWTe9bdt\\nn8hM9R1IEbenF0NAquSfQb+Jd/ALm0YQu5i3TRDJb9iO/4KjEclrHIkckLQFB32F\\niS4fpUZ732dQ0NY+1ym4Olo1/wKBgQDinjhtJ7TpSG1+TMC2W28MMbLZ0djze+cK\\n+v5XGLYLObj0ltnnOW3NSVvXFL1MH+Yks0jIKtlU5HnhN9EbMfoWX/n/JPHpYMmf\\nmbIITv8eFnw4MDWzI6D0LA6lOV6wRVkEnG347IM2E0T8QVi4dlgyN8aeZ9OXFdKj\\ncFwfxC205wKBgQDpgMpElBsl9hDltafUqHeKyHNceW0TKLIr70jp7WKrD6mYoNnr\\ndQcyRNUOTkSK98cZef583emNySJ4Xa1KWivxSyNYdOzfrQZXOH8huhIw/knZNFkq\\noZOZzKSYwvsIBnNKHtF2bYp5WV92Yl3QNai+RoJue/eWFRaKMmf2I1u3nQKBgFt7\\nM5wc41MpAcpygXkcAAAUL/buV34zDlnIaNQQDa0KaDeUjfJhpImyi3iPzrrkRaub\\nFE55vbIfaXQZbDhmPGa759evulhT0hCzosLn4OTsfsjP7V2bjGYFQwQ/3JGb7QuH\\nen16PJ2fucF7P613WLMtAOkBUNE05gtEWGwabbkRAoGAFOLFZnYuX5aj8NWXLhFF\\nFZh5wVsl6B3uRZDOFmV2X/MDAGep2bubURQVVOsOFNndSMpYCHjrpmT8xj6m8XNJ\\nWu6nxnbzzuq3po1YxwzwQgflp7YxG9d7QpMgT2QVHtf6bhdcWIGFMga9krZ4CkYn\\n3Cw93gYeOR/cVnFpGo7xb9A=\\n-----END PRIVATE KEY-----\\n\",\n" + 
			"  \"client_email\": \"firebase-adminsdk-qfw6t@cs201-project-c4168.iam.gserviceaccount.com\",\n" + 
			"  \"client_id\": \"107403988826608138469\",\n" + 
			"  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" + 
			"  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" + 
			"  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" + 
			"  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-qfw6t%40cs201-project-c4168.iam.gserviceaccount.com\"\n" + 
			"}";
	
	static String PATH="";
	
    
	
	public static void setValueTest() {
		System.out.println("test");
		String key = PATH;
		String databaseURL = "https://cs201-project-c4168.firebaseio.com";
		
		
		try {
			InputStream serviceAccount = new ByteArrayInputStream(info.getBytes(Charset.forName("UTF-8")));
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
		
		try {
			InputStream serviceAccount = new ByteArrayInputStream(info.getBytes(Charset.forName("UTF-8")));
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
		
		
		try {
			InputStream serviceAccount = new ByteArrayInputStream(info.getBytes(Charset.forName("UTF-8")));
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
		
		
		try {
			InputStream serviceAccount = new ByteArrayInputStream(info.getBytes(Charset.forName("UTF-8")));
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
		
		try {
			InputStream serviceAccount = new ByteArrayInputStream(info.getBytes(Charset.forName("UTF-8")));
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
		
		try {
			InputStream serviceAccount = new ByteArrayInputStream(info.getBytes(Charset.forName("UTF-8")));
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
		
		try {
			InputStream serviceAccount = new ByteArrayInputStream(info.getBytes(Charset.forName("UTF-8")));
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
		
		try {
			InputStream serviceAccount = new ByteArrayInputStream(info.getBytes(Charset.forName("UTF-8")));
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
		update.put(add.getID(), add);
		ref.updateChildrenAsync(update);
	}

	public static void deleteRoom(Room remove) {
		String key = PATH;
		String databaseURL = "https://cs201-project-c4168.firebaseio.com";
		
		try {
			InputStream serviceAccount = new ByteArrayInputStream(info.getBytes(Charset.forName("UTF-8")));
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
		update.put(remove.getID(), null);
		ref.updateChildrenAsync(update);

	}
	
	//adds or updates existing User to Database
	public static void updateUser(User add) {
		String key = PATH;
		String databaseURL = "https://cs201-project-c4168.firebaseio.com";
		
		try {
			InputStream serviceAccount = new ByteArrayInputStream(info.getBytes(Charset.forName("UTF-8")));
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
		try {
			InputStream serviceAccount = new ByteArrayInputStream(info.getBytes(Charset.forName("UTF-8")));
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
		Vector<Room> output = new Vector<Room>();
		try {
			InputStream serviceAccount = new ByteArrayInputStream(info.getBytes(Charset.forName("UTF-8")));
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
		try {
			InputStream serviceAccount = new ByteArrayInputStream(info.getBytes(Charset.forName("UTF-8")));
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
        try {
        	InputStream serviceAccount = new ByteArrayInputStream(info.getBytes(Charset.forName("UTF-8")));
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
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
              @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChild(id)) {
                  output.add(snapshot.child(id).getValue(User.class));
                }
                semaphore.release();
              }

            @Override
            public void onCancelled(DatabaseError arg0) {
                // TODO Auto-generated method stub
                
            }
            });
        
        try {
            semaphore.acquire();
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