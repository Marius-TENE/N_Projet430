package agpe.portfolio.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import agpe.metier.AgpeMetier;
import agpe.modeles.Departement;
import agpe.modeles.Etablissement;
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
	public ModelAndView AfficherFormulaireCretionPortfolio() {
		ModelAndView mav = new ModelAndView();
		mav.clear();
		mav.setViewName("pages/creer_portfolio");
		ArrayList<Etablissement> etablissements = agpeMetier.listeEtablissement();
		ArrayList<Departement> departements = agpeMetier.listerTousLesDepartements();
		mav.addObject("departements", departements);
		mav.addObject("etablissements", etablissements);
		return mav;
		
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
		user.setNaissance(form.getNaissance().toString());
		user.setGrade(form.getGrade());
		user.setSpecialite(form.getSpecialite());
		user.setSexe(form.getSexe());
		user.setActif(1);
		user.setLogin(form.getMatricule());
		user.setPassword(form.getEmail());
		user.setRole("enseignant");
		user.setDepartement(agpeMetier.chercherDepartementAvecId(form.getIdDepartement()));
		String resultat="";
		int indice=0;
		try {
			agpeMetier.enregistrerUtilisateur(user);
			resultat="Nouveau portfolio crée avec succès.";
		} catch (Exception e) {
			resultat="Echec lors de la création du nouveau portfolio. Verifiez les informations et réessayez !";
			indice=1;
		}
		
		return "redirect:/creation_nouveau_portfolio";
	}	

}
