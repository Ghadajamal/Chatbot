package chatDelivery;

import java.util.Observable;
import java.util.Observer;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import javax.mail.Session;


/*public class EmailNotifier {
    public void update(String email) {
        // Send an email to the user
        System.out.println("Sending email to " + email + "...");
        // Code to send email
    }

}*/

public class EmailNotifier implements Observer {
	
    private static final String EMAIL_HOST = "smtp.office365.com";
    private static final int EMAIL_PORT = 587;
    private static final String EMAIL_USERNAME = "ghadajamal345@outlook.com";
    private static final String EMAIL_PASSWORD = "ghada123";
    //String recipient = "jamalghada85@gmail.com";

	    
	    
    public void update(Observable obs, Object obj) {
        String email = (String) obj;
        
        try {
        	System.out.println("hey this is update");
            sendEmail(email);
        } catch (Exception e) {
            System.err.println("Failed to send email notification: " + e.getMessage());
        }
    }
	    
	    
	    private void sendEmail(String recipient) throws MessagingException {
	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", EMAIL_HOST);
	        props.put("mail.smtp.port", EMAIL_PORT);
	        
	       // props.put("mail.smtps.socketFactory.port", "465");
	        //props.put("mail.smtps.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        
	        
	        //TEST
	        Authenticator auth = new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
	            }
	        };
	        //END TEST
	        
	        
	        
	        System.out.println("hey this is right before session");
	        Session session = Session.getInstance(props, auth);
			try {
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(EMAIL_USERNAME));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
	        message.setSubject("Confirmation email from the chatbot");
	        message.setText("Thank you for using our chatbot. We are happy that you expressed your interest  in our products. We would like to inform you that your request has been received and we will notify you again as soon as it's processed. \n  Sincerely.");

	        Transport.send(message);

	        System.out.println("Email sent successfully.");
        } catch (Exception e) {
        	System.out.println("the error is : " + e);
           // e.printStackTrace();
        }
	    }
	}


/*public void update(String message) {
String[] parts = message.split(":");
if (parts.length > 1) {
    String email = parts[1];
    sendConfirmationEmail(email);
}
}*/



// @Override
/*public void update(Observable observable, Object arg) {
// code to send email notification
String email = (String) arg;
System.out.println("Sending email notification to " + email);
}*/
