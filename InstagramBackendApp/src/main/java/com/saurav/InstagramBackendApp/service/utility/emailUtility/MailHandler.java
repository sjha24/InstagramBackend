package com.saurav.InstagramBackendApp.service.utility.emailUtility;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
public class MailHandler {
    public static void sendMail(String userEmail, String emailTesting, String tokenValue) {
        String host = "smtp.gmail.com";

        Properties prop = System.getProperties();
        System.out.println(prop);

        prop.put("mail.smtp.host" , host);
        prop.put("mail.smtp.port" , "465");
        prop.put("mail.smtp.ssl.enable" , "true");
        prop.put("mail.smtp.auth" , "true");
//        Creating session
        Session session = Session.getInstance(prop, new MailAuthenticator()) ;
//        Create the message object
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(MailConstant.SENDER);
//            for(String receiver : MailConstant.RECEIVER){
////                To multiple receipients
//                mimeMessage.addRecipient(Message.RecipientType.TO , new InternetAddress(receiver));
//                System.out.println(receiver);
//            }
//            To single receipient
            mimeMessage.setRecipient(Message.RecipientType.TO , new InternetAddress(userEmail));
            mimeMessage.setSubject(emailTesting);

            mimeMessage.setText(tokenValue);
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
