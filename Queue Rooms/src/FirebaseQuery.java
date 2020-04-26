package queuerooms2;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

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
import com.google.firebase.database.Query;

public class FirebaseQuery {
	
	public void addAlert() {
		String a = "this is a test\n";
		FirebaseDatabase myFirebase = FirebaseDatabase.getInstance();
		DatabaseReference ref = myFirebase.getReference("testid");
		ref.child("test").setValueAsync(a);
	}
	
	public List<Alert> query(String root) throws Exception {
		
		String key = root + "cs201-project-c4168-firebase-adminsdk-qfw6t-b660ec6eb8";
		String databaseURL = "https://cs201-project-c4168.firebaseio.com/";
		
		FileInputStream serviceAccount =
				  new FileInputStream(key);

		FirebaseOptions options = new FirebaseOptions.Builder()
				  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				  .setDatabaseUrl(databaseURL)
				  .build();

		FirebaseApp.initializeApp(options);
		
		FirebaseDatabase myFirebase = FirebaseDatabase.getInstance();
		List<Alert> data = new ArrayList<Alert>(); 
		
		
		
		DatabaseReference ref = myFirebase.getReference("alerts");
		Query q = ref.orderByValue();
		
		CountDownLatch done = new CountDownLatch(1);
		q.addChildEventListener(new ChildEventListener() {
			
			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
				for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
					Alert a = dataSnapshot.getValue(Alert.class);
					data.add(a);
				}
				done.countDown();
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
		done.await(); // wait for response 
		
		return data;
	}
	
	
	public static void test() throws Exception{
		String key = "WebContent/mytest1-ab6ae-firebase-adminsdk-hk5br-401f095bd6.json";
		String databaseURL = "https://mytest1-ab6ae.firebaseio.com";
		
		FileInputStream serviceAccount =
				  new FileInputStream(key);

		FirebaseOptions options = new FirebaseOptions.Builder()
				  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				  .setDatabaseUrl(databaseURL)
				  .build();

		FirebaseApp.initializeApp(options);
		
		FirebaseAuth auth = FirebaseAuth.getInstance();
		UserRecord ur = auth.getUser("2");
		System.out.println("Successfully fetched user data: " + ur.getEmail());
	}
	
	public static void main(String args[]) {
		try {
			test();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
