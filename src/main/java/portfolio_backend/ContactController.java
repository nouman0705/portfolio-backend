package portfolio_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ContactController {

	@Autowired
	public EmailService emailService;
	
	@PostMapping("/contact")
	public ResponseEntity<String> handleContact(@RequestBody ContactRequest request){
		try {
			emailService.sendContactEmail(request);
			return ResponseEntity.ok("Message sent!");
		}catch(Exception e ) {
			return ResponseEntity.status(500).body("Error : " + e.getMessage());
		}
	}
}
