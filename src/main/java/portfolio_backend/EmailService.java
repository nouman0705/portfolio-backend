package portfolio_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String toEmail;
	
	public void sendContactEmail(ContactRequest request) {
		try {
            System.out.println("=== Trying to send email ===");
            System.out.println("To: " + toEmail);
            System.out.println("From: " + request.getEmail());
            System.out.println("Subject: " + request.getSubject());

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setReplyTo(request.getEmail());
            message.setSubject("Portfolio Contact: " + request.getSubject());
            message.setText(
                "New message from your portfolio!\n\n" +
                "Name    : " + request.getFirstName() + " " + request.getLastName() + "\n" +
                "Email   : " + request.getEmail() + "\n" +
                "Subject : " + request.getSubject() + "\n\n" +
                "Message :\n" + request.getMessage()
            );

            mailSender.send(message);
            System.out.println("=== Email sent successfully! ===");

        } catch (Exception e) {
            System.out.println("=== EMAIL ERROR ===");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
		
	}
}
