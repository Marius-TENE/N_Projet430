package agpe.authentification.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import agpe.authentification.model.PasswordResetToken;
import agpe.metier.AgpeMetier;

@Controller
@RequestMapping("/restaurer-mot-passe")
public class PasswordResetController {
	
	
	@Autowired
	private AgpeMetier agpeMetier;
	
	@GetMapping
	public String AfficherFormulaireDefinitionNouveauMotPasse(@RequestParam(required = false) String token
			,Model model) {
		
		PasswordResetToken resetToken = agpeMetier.findByToken(token);
		if(resetToken==null) {
			model.addAttribute("error","impossible de trouver le jeton de restauration du mot de passe");
		}else if(resetToken.isExpired()) {
			model.addAttribute("error","le jeton a expiré, svp faite une nouvelle demande de restauration de mot de passe");
		}else {
			model.addAttribute("token",resetToken.getToken());
		}
		
		return "restaurer-mot-passe";
		
	}
	
	@PostMapping
	@Transactional
	public String handlePassordReset()
}
