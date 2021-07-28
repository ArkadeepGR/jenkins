package com.example.AppCompany.User;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserService {

	@Autowired
	UserRepository userRepo;
	
	public User1 getUserById(long id) {
		return userRepo.findById(id).get();
	}
	
	public User1 saveNewUser(User1 req) {
		req.setConfirmed(false);
		req.setRole("ROLE_USER");
		req.setAdmin(false);
		User1 user=userRepo.save(req);
		try {
			sendemail(user.getId());
		}catch(Exception e) {
			System.out.println(e);
		}
		return user;
	}
	
	public User1 saveUpdatedUser(User1 req) {
		User1 user=userRepo.save(req);
		return user;
	}
	
	public String setConfirmUser(long id) {
		try {
		User1 user=userRepo.findById(id).get();
		user.setConfirmed(true);
		userRepo.save(user);
		return "Success";
		}
		catch(Exception e){
			return "Failed";
		}
	}

	
	public void sendemail(Long userid) throws javax.mail.internet.AddressException, MessagingException {

	      User1 user = userRepo.getById(userid);	
	      final String username = "mynodeindia@gmail.com";
	      final String password = "Indianode@123";

			Properties prop = new Properties();
			prop.put("mail.smtp.host", "smtp.gmail.com");
			prop.put("mail.smtp.port", "587");
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.starttls.enable", "true"); 

			Session session = Session.getInstance(prop,
					new javax.mail.Authenticator() {
					protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new javax.mail.PasswordAuthentication(username, password);
				}
			});

			try {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("mynodeindia@gmail.com"));
				//message.setRecipients(
					//	Message.RecipientType.TO,
					//	InternetAddress.parse("sftrainerram@gmail.com")
					//	);
				message.setRecipients(
						Message.RecipientType.TO,
						InternetAddress.parse(user.getEmail())
						);
				message.setSubject("User confirmation email");
				//     message.setText("Dear Mail Crawler,"
				//           + "\n\n Please do not spam my email!");
				message.setContent(
						"<h1><a href ='http://localhost:8081/confirm-user/"+userid+"'> Click to confirm </a></h1>",
						"text/html");
				Transport.send(message);

				System.out.println("Done");

			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	
}
