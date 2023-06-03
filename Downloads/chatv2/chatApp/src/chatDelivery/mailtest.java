package chatDelivery;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class mailtest {
	public static void main(String[] args) {
        String username = "aminechatbot@hotmail.com";
        String password = "amine123";
        String recipient = "jamalghada85@gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.auth", "true");
	    //props.put("mail.smtps.socketFactory.port", "587");
        //props.put("mail.smtps.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        Session session = Session.getInstance(props, auth);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Test email from Java");
            message.setText("Hello, this is a me amine love you from Java!");

            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
