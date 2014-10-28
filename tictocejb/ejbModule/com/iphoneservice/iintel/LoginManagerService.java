/**
 * 
 */
package com.iphoneservice.iintel;

import java.util.List;
import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.criterion.Property;

import com.iphoneservice.iintel.entity.TTUsers;
import com.iphoneservice.iintel.entity.TtuserConnection;

/**
 * @author przaca
 * 
 */

@LocalBean
@Stateless
public class LoginManagerService {
	@PersistenceContext(unitName = "mypersistence")
	private EntityManager em;

	public String login(TTUsers user) {
		Session session = ((org.hibernate.ejb.EntityManagerImpl) em
				.getDelegate()).getSession();
		if (userExists(user)) {
			return checkPassword(user);
		} else {
			return "userdoesnotexist";
		}

	}

	public boolean userExists(TTUsers user) {

		Session hbsession = ((org.hibernate.ejb.EntityManagerImpl) em
				.getDelegate()).getSession();
		List<TTUsers> userFromDBList = hbsession
				.createCriteria(TTUsers.class)
				.add(Property.forName("username").eq(
						user.getUsername())).list();
		TTUsers userFromDB=userFromDBList.get(0);
		if (userFromDB == null) {
			return false;
		}
		return true;
	}

	public String checkPassword(TTUsers user) {
//TODO:Z
		Session hbsession = ((org.hibernate.ejb.EntityManagerImpl) em
				.getDelegate()).getSession();
		List<TTUsers> userFromDBList = hbsession
				.createCriteria(TTUsers.class)
				.add(Property.forName("username").eq(
						user.getUsername())).list();
		TTUsers userFromDB=userFromDBList.get(0);
		if (userFromDB != null) {
			if(user.getPassword().equals(userFromDB.getPassword())){
				return "loginsuccessful";
			}else{
				return "wrongpassword";
			}
		}
		return "userdoesnotexist";
		
	}

	public String sendForgottenPassword(TTUsers user) {
		Session hbsession = ((org.hibernate.ejb.EntityManagerImpl) em
				.getDelegate()).getSession();
		List<TTUsers> userFromDBList = hbsession
				.createCriteria(TTUsers.class)
				.add(Property.forName("username").eq(
						user.getUsername())).list();
		TTUsers userFromDB=userFromDBList.get(0);
		// TODO Auto-generated method stub
		// Recipient's email ID needs to be mentioned.
	      String to = "zwaker@gmail.com";

	      // Sender's email ID needs to be mentioned
	      String from = "web@taptoc.com";

	      // Assuming you are sending email from localhost
	      String host = "localhost";

	      Properties props = new Properties();
	      props.put("mail.smtp.host", "smtp.gmail.com");
	      props.put("mail.smtp.socketFactory.port", "465");
	      props.put("mail.smtp.socketFactory.class",
	      "javax.net.ssl.SSLSocketFactory");
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.port", "465");
	       
	      //Session session = 

	      // Get the default Session object.
	      javax.mail.Session session = javax.mail.Session.getInstance(props,
	    	      new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		      return new PasswordAuthentication("tapntoc@gmail.com","rejowana");
		      }
		      });

	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject("TapToc - Your password");

	         // Now set the actual message
	         message.setText("Your password is "+ userFromDB.getPassword());

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
		return "sent";
	}
}
