package portfolio_backend;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.*;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${SENDGRID_API_KEY}")
    private String sendGridApiKey;

    public void sendContactEmail(ContactRequest request) throws Exception {
        Email from = new Email("nouman0705@gmail.com");
        Email to = new Email("nouman741740@gmail.com");
        Content content = new Content("text/plain",
            "First Name : " + request.getFirstName() +
            "\nLast Name : " + request.getLastName()+
            "\nEmail : " + request.getEmail() +
            "\nSubject : " + request.getSubject() +
            "\nMessage : " + request.getMessage());
        Mail mail = new Mail(from, request.getSubject(), to, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request sgRequest = new Request();
        sgRequest.setMethod(Method.POST);
        sgRequest.setEndpoint("mail/send");
        sgRequest.setBody(mail.build());
        sg.api(sgRequest);
    }
}