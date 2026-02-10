package in.sp.spring.service;

import java.util.Properties;

//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;



@Service
public class EmailService {
	  
	public boolean sendEmail(String subject , String message , String to ) {
		boolean f=  false;
		String from = "ssivamayadav0123@gmail.com";
		// variable for email 
		String host = "smtp.gmail.com";
		 //get the system properties
		 Properties properties = System.getProperties();
		 System.out.println("PROPERTIES " +properties);
		 
		 
		  // host set information  
		 properties.put("mail.smtp.host",host);
		 properties.put("mail.smtp.port","465");
		 properties.put("mail.smtp.ssl.enable","true");
		 properties.put("mail.smtp.auth","true");
		  
		  // step : 1 to get the session object 
		 Session session = Session.getInstance(properties ,new Authenticator(){
			   @Override
			    protected PasswordAuthentication getPasswordAuthentication() {
				   return new PasswordAuthentication("ssivamyadav0123@gmail.com","zyla qwwz rlrt nbri");
			   }
			 
		 });
				  
		 session.setDebug(true);
		  
		  // compose the media text to messaage ; 
		  MimeMessage m  = new MimeMessage(session);
		  try {
			  // from email
			   m.setFrom(from);
			    //addind receimpt
			    m.addRecipient(Message.RecipientType.TO , new InternetAddress(to));
			    
			    // adding subject 
			   
			    m.setSubject(subject);
			     //adding text 
			    m.setContent(message, "text/html; charset=UTF-8");

			     // send message using transport class
			      Transport.send(m);
			      
			       System.out.println("send message.........");
			        f = true;
			         
			     
		  }   catch(Exception e){
			   e.printStackTrace();
			  
		  }
		   return f;
		   
		 
	}

	
	

}
