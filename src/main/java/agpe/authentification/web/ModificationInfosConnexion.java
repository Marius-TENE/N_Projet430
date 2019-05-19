package agpe.authentification.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import agpe.authentification.model.PasswordResetToken;
import agpe.authentification.service.EmailService;
import agpe.authentification.web.dao.PasswordResetDto;
import agpe.metier.AgpeMetier;
import agpe.modeles.Utilisateur;

@Controller
@RequestMapping("/modification_infos_connexion")
public class ModificationInfosConnexion {
	
	@Autowired
	private AgpeMetier agpeMetier;
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping
	public String afficherFormulaireModificationInfosConnexion() {
		return "pages/modifier_infos_connexion";
	}
	
	@PostMapping
	@Transactional
	public String handlePassordReset(@ModelAttribute("modification_info_connexionForm") ModificationInfosConnexion form,HttpSession httpSession) {
		Utilisateur user = (Utilisateur) httpSession.getAttribute("user");
		if(form.ge)
		System.out.print("\n\n"+user.toString()+"\n");
		agpeMetier.ModifierMotPasse(form.getPassword(),user.getMatricule(),user.getTel());
		agpeMetier.supprimerToken(token);
		
		return "redirect:/connexion";
	}
	
}
