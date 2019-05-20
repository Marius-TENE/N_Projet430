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
@RequestMapping("/creation_nouveau_portfolio")
public class CreerPortfolioDaoController {

	@Autowired
	private AgpeMetier agpeMetier;
	
	@ModelAttribute("creation_portfolioForm")
	public CreerPortfolioDao CreationNouveauPortfolio() {
		return new CreerPortfolioDao();
	}
	
	@GetMapping
	public String AfficherFormulaireModificationInfosPersonnelles() {
		return "pages/creer_portfolio";
		
	}
	
	@PostMapping
	@Transactional
	public String handlePassordReset(@ModelAttribute("creation_portfolioForm") CreerPortfolioDao form) {
		Utilisateur user = new Utilisateur();
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
		user.setActif(1);
		user.setLogin(form.getMatricule());
		user.setPassword(form.getEmail());
		user.setRole("enseignant");
		System.out.print("\n\n"+form.toString()+"\n");
		agpeMetier.enregistrerUtilisateur(user);
		return "redirect:/creer_portfolio";
	}	

}
