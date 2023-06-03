package chatDelivery;

//import java.net.Authenticator;
//import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Session;
//import com.mysql.cj.protocol.Message;
import javax.mail.internet.MimeMessage;





import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.MessagingException;
//import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.util.Properties;
import javax.mail.Authenticator;




public class Observable {
	
	private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    
    
    
    public void notifyObservers(String message) {
    	System.out.println("yooooo");
        for (Observer observer : observers) {
        	
            observer.update(message);
            
        }
    }

   /*public void update(String message) {
        String[] parts = message.split(":");
        if (parts.length > 1) {
            String email = parts[1];
            sendConfirmationEmail(email);
        }
    }*/
    
   
   /*public class MyAuthenticator extends Authenticator {
	    private String username;
	    private String password;

	    public MyAuthenticator(String username, String password) {
	        this.username = username;
	        this.password = password;
	    }

	    @Override
	    protected PasswordAuthentication getPasswordAuthentication() {
	        return new PasswordAuthentication(username, password);
	    }
	}*/

   
   
   
   /* private void sendConfirmationEmail(String email) {
        final String username = "fluffyexobbh@gmail.com"; // your email
        final String password = "@Changwook@123"; // your email password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // change to your email provider's SMTP server
        props.put("mail.smtp.port", "587"); // change to your email provider's SMTP port

        //Session session = Session.getInstance(props);
        Session session = Session.getInstance(props, new MyAuthenticator(username, password));

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Product Back In Stock Notification");
            message.setText("Dear User," + "\n\nWe are pleased to inform you that the requested product is now back in stock. Thank you for your patience and interest in our products." + "\n\nBest regards,\nThe Product Team");

            Transport.send(message);
            System.out.println("Confirmation email sent successfully to " + email);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    */
    
    

  

}








