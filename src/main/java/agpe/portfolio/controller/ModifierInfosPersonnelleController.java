package agpe.portfolio.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import agpe.metier.AgpeMetier;
import agpe.modeles.Utilisateur;
import agpe.portfolio.modele.CreerPortfolioDao;

@Controller
@RequestMapping("/modification_infos_personnelles")
public class ModifierInfosPersonnelleController {

	@Autowired
	private AgpeMetier agpeMetier;
	
	@ModelAttribute("modefierInfosPersonnellesForm")
	public CreerPortfolioDao modifierInfosPersonnelles() {
		return new CreerPortfolioDao();
	}
	
	@GetMapping
	public String AfficherFormulaireModificationInfosPersonnelles() {
		return "pages/modifier_infos_personnelles_enseignant";
		
	}
	
	@PostMapping
	@Transactional
	public String handlePassordReset(@ModelAttribute("modefierInfosPersonnellesForm") CreerPortfolioDao form,HttpSession httpSession) {
		Utilisateur user = (Utilisateur) httpSession.getAttribute("user");
		httpSession.removeAttribute("user");
		user.setMatricule(form.getMatricule());
		user.setNom(form.getNom());
		user.setPrenom(form.getPrenom());
		user.setEmail(form.getEmail());
		user.setAdresse(form.getAdresse());
		user.setTel(form.getTel());
		user.setNaissance(form.getNaissance());
		user.setGrade(form.getGrade());
		user.setSpecialite(form.getSpecialite());
		user.setSexe(form.getSexe());
		//System.out.print("\n\n"+form.toString()+"\n");
		agpeMetier.enregistrerUtilisateur(user);
		httpSession.setAttribute("user",user);
		return "redirect:/modification_infos_personnelles";
	}	

}
