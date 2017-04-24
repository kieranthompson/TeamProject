package core;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Email {

    public Email() {

    }

    public static void emailReceipt(String from, String password, String to, String subject, String content) throws MessagingException{

        String host = "smtp.gmail.com";
        String smtpPort = "587";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.user", from);
        properties.setProperty("mail.smtp.password", password);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.setProperty("mail.smtps.ssl.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                from, password);// Specify the Username and the PassWord
                    }
                });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
        message.setSubject(subject);
        message.setText(content);



        Transport transport = session.getTransport("smtp");
        transport.connect(null, from, password);
        transport.send(message);
        System.out.println("message sent successfully....");
    }




}
