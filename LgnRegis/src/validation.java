import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.*;

import javax.servlet.http.HttpServletRequest;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class validation {
	
	public static  String Login_check(String email,String pass)
	{
		try {
			String info="{\n" + 
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
			
			
            InputStream serviceAccount = new ByteArrayInputStream(info.getBytes(Charset.forName("UTF-8")));

            // Initialize the app with a service account, granting admin privileges
            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://cs201-project-c4168.firebaseio.com/")
                .build();
            FirebaseApp.initializeApp(options);

            // As an admin, the app has access to read and write all data, regardless of Security Rules
            DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("restricted_access/secret_document");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
              @Override
              public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.getValue();
                System.out.println(document);
              }

              @Override
              public void onCancelled(DatabaseError error) {
              }
            });
            }catch(Exception e)
            {
            	e.printStackTrace();	
            }
		
		
		UserRecord userRecord;
		try {
			userRecord = FirebaseAuth.getInstance().getUserByEmail(email);
			System.out.println("Successfully fetched user data: " + userRecord.getUid());
			System.out.println(userRecord.getUid() + " " + userRecord.getEmail() + " " + userRecord.getDisplayName() 
			+ " ");
			String pwd=userRecord.getDisplayName();
			pwd=pwd.substring(pwd.indexOf("password=") + 9, pwd.length());
	        System.out.println(pwd);
	        System.out.println(pwd + " " + pass);
	        if(pwd.equals(pass))
	        {
	        	System.out.println("Sucess");
	        	String temp = (String)userRecord.getUid();
	        	return temp;
	        }
	        else
	        {
	        	System.out.println("Failure");
	        	return null;
	        }
		} catch (FirebaseAuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Invalid email!");
			return null;
		}
		               
	    
	}
	
	public static boolean regis_check(String email)
	{
		try {
			String info="{\n" + 
					"  \"type\": \"service_account\",\n" + 
					"  \"project_id\": \"mytest1-ab6ae\",\n" + 
					"  \"private_key_id\": \"401f095bd62951d59cc9a6f5810cd692776d7a75\",\n" + 
					"  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCdCQ1R6cSdfvQ9\\nFJBsxinUKIBc8vkxWXCgzuBaLEuaplC7xj54QtSsYa/DzqSuvUrcHIWLeaWnr3E6\\nPGVLQFa0ogetX0W2wFCqN/yobiVYUyZODywhBkv0mAIBUDrIuFzQrOY395ZYx/Uf\\ns6mpqLQyL5jIVvQl3G3NO2uRUsMpFJsIXDyNgYFjm+YJLaetWKOsd0mFMI8KqCoP\\nDIZE/HWdNLjsZA4e8ep38K/z5//VlbvM5HiSi+tILsoDBYwrnEGx90x3m4kH0R8Q\\nqWNnwpjRCoQFXTZSV262Ky50VlEXXlXeMCuS5UayxJkD+7I4cKywnmYwLa2m98sG\\nxOkbyDTXAgMBAAECggEARGnGp0qQMUs3TYrxdqoIXBj+/z+NZ73+7tfh7vcAQxI0\\n9vFhktQOwkDWqZgTr9rgy68TURXAoSKyUfww5w1TGOPEM3hfr0PugJHenWRLGcUz\\n2WsLvyF2oss8iGTzDzIeoXzJ2VUgZ3JJrLOBdVv9kSWiSplzSTFUlsZN0SR8yJ62\\nL7Pw0KYkdiYm3+GqnLnaXw42IZsiCbsplI6K95G1GejFiVxbN5Dxq79cpF5HKSiW\\n75UgV2jA93s+HijVrKNLip/tH/MkmOY8Yx3KLn5GtaKS8WKrW/szUHTOmS68d2vv\\nEgs1X5KTqcWrUNJ6egD6zIyj7nJCIe/zWPsz0ePRfQKBgQDQDeBP4+EuxqSD5x7m\\ncMO/jhRji+RNnrEKk0+pCk0meM4CFCnxpXojzIabw+3j1BT8+IxPiTlhhaxsAUIn\\nQYLXt+dU6M1Kg1zacUkZip0DhMUkG3NTi0deOSyy/cDpPnFu0RNm0pqmyAjzEvlt\\nCsLvmHYnWfSqEk2dzh3ohepfuwKBgQDBOVPReLz24+bfK/AmCUbqSc3+fBYUhRwA\\nvHEN5aW0a2I7SdksABA32nbpGj0wrsBIsEXHuIpDtpvxS+Ia6ECjxP7yY9CFbl4H\\neDSZCIjulIgvngtuTyAH2Q3Ss1KPlsA4I0LRTkyqt9eQWKbSGw5fzNvxW88pg+Nd\\nhxD3nksnlQKBgEsI9iFZ+li4Y5T+wfIyMSbgwxGz3j7wQMGjp1M7199MKmAtjYMN\\nPyOyG8oJu74zOu6RZWk6AGvfnfPF8GngAqRyOwgwffmlrtNJkCeaCk6+j6saC1nS\\nHYcp76kOQ4jbcGWbGfg3dHc6JaAk87gT7YIj5lkC5G6B82Q6dibqRUEJAoGBAKHT\\nCE7L5r6DGi1YWBUS+fBoBXyXocpnvI8lQy3XYhMbSDbNX3PJTcvpTGNnVU37XRDD\\niH1/IVUaj7njyqasUs6VwstwKwhE6b0pwCtWiY/+l5wBqiMlY3cPItOO2ZtmLvAV\\nH/W5tN3ZgFDk3jni7QPwx1d5g6OKUJZY8y3exaSNAoGBALZ/YTPloffoo/mTg/c/\\n8tsMnUkHgwHKLQZLl/cY62qJfWhrOpwUwZJnFMterasR7LaLMVdzfGa8ni3tdQYm\\naBGSCbRAUS8RdcEtVT4KkUQ4H8iSN0bIWA8xlWf7icyMvVoWA8yrgWXFxjEO6hjd\\nEFHEkZiSshyQnII+aFB6uy59\\n-----END PRIVATE KEY-----\\n\",\n" + 
					"  \"client_email\": \"firebase-adminsdk-hk5br@mytest1-ab6ae.iam.gserviceaccount.com\",\n" + 
					"  \"client_id\": \"103575953294035177705\",\n" + 
					"  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" + 
					"  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" + 
					"  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" + 
					"  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-hk5br%40mytest1-ab6ae.iam.gserviceaccount.com\"\n" + 
					"}\n" + 
					"";
			
			
            InputStream serviceAccount = new ByteArrayInputStream(info.getBytes(Charset.forName("UTF-8")));

            // Initialize the app with a service account, granting admin privileges
            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://mytest1-ab6ae.firebaseio.com/")
                .build();
            FirebaseApp.initializeApp(options);

            // As an admin, the app has access to read and write all data, regardless of Security Rules
            DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("restricted_access/secret_document");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
              @Override
              public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.getValue();
                System.out.println(document);
              }

              @Override
              public void onCancelled(DatabaseError error) {
              }
            });
            }catch(Exception e)
            {
            	e.printStackTrace();	
            }
		
		
		UserRecord userRecord;
		try {
			userRecord = FirebaseAuth.getInstance().getUserByEmail(email);
			System.out.println("Successfully fetched user data: " + userRecord.getUid());
			return false;
		} catch (FirebaseAuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("email not found, so we can regis!");
			return true;
		}             
	    
	}

}
