package agpe.authentification.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import agpe.authentification.service.EmailService;
import agpe.authentification.web.dao.ModificationInfosConnexionDao;
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
		ModelAndView mav = new ModelAndView();
		return "pages/modifier_infos_connexion";
	}
	
	
	@ModelAttribute("modification_info_connexionForm")
	public ModificationInfosConnexionDao PasswordReset() {
		return new ModificationInfosConnexionDao();
	}
	
	@PostMapping
	@Transactional
	public ModelAndView handleModifierInfosConnexion(@ModelAttribute("modification_info_connexionForm") ModificationInfosConnexionDao form,HttpSession httpSession) {
		Utilisateur user = (Utilisateur) httpSession.getAttribute("user");
		ModelAndView mav = new ModelAndView();
		mav.clear();
		mav.setViewName("pages/modifier_infos_connexion");
		
		if(form.getPassword().compareTo(form.getConfirmpassword())!=0) {
			mav.addObject("password_diff", "Les mots de passe ne correspondent pas !");
			mav.addObject("login", user.getLogin());
			return mav;
		}
		else {
			mav.addObject("succes","Vos informations ont été enregistrées avec succès !");
			mav.addObject("login",user.getLogin());
			user.setLogin(form.getLogin());
			user.setPassword(form.getPassword());
			agpeMetier.enregistrerUtilisateur(user);
			httpSession.removeAttribute("user");
			httpSession.setAttribute("user", user);
			return mav;
		}
	 	
	}
	
}
