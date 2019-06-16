package agpe.authentification.web;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import agpe.authentification.model.Mail;
import agpe.authentification.model.PasswordResetToken;
import agpe.authentification.service.EmailService;
import agpe.metier.AgpeMetier;
import agpe.modeles.Utilisateur;

@Controller
@RequestMapping("/motpasse-oublie")
public class PasswordForgotController {
	
	@Autowired
	private AgpeMetier agpeMetier;
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping
	public String afficherFormulaireMotPasseOublie() {
		return "pages/forgotpassword";
	}
	
	@PostMapping
	public String processusEnvoiFormulaireRestaurationMotPasse(Model model1,String email,HttpServletRequest request) {
		
		Utilisateur user = agpeMetier.chercherUtilisateurAvecEmail(email);
		if(user!=null) {
			PasswordResetToken token = new PasswordResetToken();
			token.setToken(UUID.randomUUID().toString());
			token.setUser(user);
			token.setExpiryDate(30);
			agpeMetier.enregisterToken(token);
			
			Mail mail = new Mail();
			mail.setFrom("agpe.uy1@gmail.com");
			mail.setTo(user.getEmail());
			mail.setSubject("Requête de restauration de mot de passe");
			
			Map<String, Object> model = new HashMap<>();
			model.put("token", token);
			model.put("user", user);
			model.put("signature","Tous droits reservés © agpe, LLC. All rights reserved.");
			String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
			model.put("resetUrl",url+"/restaurer-mot-passe?token="+token.getToken());
			
			mail.setModel(model);
			emailService.sendEmail(mail);
			return "redirect:/motpasse-oublie";
			
		}
		else {
			model1.addAttribute("email",email);
			model1.addAttribute("msg","utilisateur introuvable");
			return "pages/forgotpassword";
		}
		
	}
}
