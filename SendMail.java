package GetContent;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail implements Service {

	//Method to send mail via AWS SES SMTP
	  public String doService(String email,
			  				   String subject,
			  				   String content)
	  				throws Exception
	  {
		  String fromId = "VERIFIED_SENDER_MAIL_ID";   // Verified sender mail id
		  String toId = email;   // Recipient id obtained dynamically.
		    					 // If production access not acquired, this mail id should also be verified.		  
		  String body = content; // Dynamically obtained
		  String sub = subject; // Dynamically obtained
		  String SMTP_USERNAME = "SES_USERNAME"; // Configured as in AWS SES
		  String SMTP_PASSWORD = "SES_PASSWORD"; // Configured as in AWS SES
		  String HOST = "email-smtp.us-west-2.amazonaws.com"; //SMTP URL
		  int PORT = 587;
		  
		// Create a Properties object to contain connection configuration information.
	    	Properties props = System.getProperties();
	    	props.put("mail.transport.protocol", "smtp");
	    	props.put("mail.smtp.port", PORT);
	    	
	    	// Set properties indicating that we want to use STARTTLS to encrypt the connection.
	    	// The SMTP session will begin on an unencrypted connection, and then the client
	        // will issue a STARTTLS command to upgrade to an encrypted connection.
	    	props.put("mail.smtp.auth", "true");
	    	props.put("mail.smtp.starttls.enable", "true");
	    	props.put("mail.smtp.starttls.required", "true");

	        // Create a Session object to represent a mail session with the specified properties. 
	    	Session session = Session.getDefaultInstance(props, null);

	        // Create a message with the specified information. 
	        MimeMessage msg = new MimeMessage(session);
	        msg.setFrom(new InternetAddress(fromId));
	        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toId));
	        msg.setSubject(sub);
	        msg.setContent(body,"text/plain");
	            
	        // Create a transport.        
	        Transport transport = session.getTransport();
	   
	        // Send the message.
	        try
	        {
	            System.out.println("Attempting to send an email through the Amazon SES SMTP interface...");
	            
	            // Connect to Amazon SES using the SMTP user name and password you specified above.
	            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
	        	
	            // Send the email.
	            transport.sendMessage(msg, msg.getAllRecipients());
	            System.out.println("Email sent!");
	            return "Mail sent Successfully :)";
	        }
	        catch (Exception ex) {
	        	String errmsg;
	            System.out.println("The email was not sent.");
	            errmsg = "Mail not sent: " + ex.getMessage();
	            System.out.println(errmsg);	            
	            return errmsg;
	        }
	        finally
	        {
	            // Close and terminate the connection.
	            transport.close();        	
	        }
	  }
}
