package agpe.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderImplementation implements MailSender{
	
	private JavaMailSender javaMailSender;
	
	@Autowired
	public MailSenderImplementation(JavaMailSender javaMailSender) {
		this.javaMailSender=javaMailSender;
	}

	@Override
	public void envoyerMail(MailRequest mailRequest) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(mailRequest.getEmailRecepeteur());
		mail.setFrom("agpe.uy1@gmail.com");
		mail.setSubject(mailRequest.getObjet());
		mail.setText(mailRequest.getMessage());
		javaMailSender.send(mail);
	}

}
