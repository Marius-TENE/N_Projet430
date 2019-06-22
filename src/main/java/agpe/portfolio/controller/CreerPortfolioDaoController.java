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
	public ModelAndView handlePassordReset(@ModelAttribute("creation_portfolioForm") CreerPortfolioDao form) {
		ModelAndView mav = new ModelAndView();
		mav.clear();
		mav.setViewName("pages/creer_portfolio");
		ArrayList<Etablissement> etablissements = agpeMetier.listeEtablissement();
		ArrayList<Departement> departements = agpeMetier.listerTousLesDepartements();
		mav.addObject("departements", departements);
		mav.addObject("etablissements", etablissements);
		if(agpeMetier.existanteUtilisateur(form.getMatricule())==false) {
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
			user.setPassword(form.getMatricule());
			user.setRole("enseignant");
			user.setDepartement(agpeMetier.chercherDepartementAvecId(form.getIdDepartement()));
			String resultat="";
			
			try {
				agpeMetier.enregistrerUtilisateur(user);
				mav.addObject("success", "Nouveau portfolio crée avec succès.");
			} catch (Exception e) {
				mav.addObject("echec","Echec lors de la création du nouveau portfolio. Verifiez les informations et réessayez !");
				
			}
		}
		else {
			mav.addObject("echec", "Désolé, un portfolio existe déjà avec ce matricule !");
		}
		
		return mav;
	}	

}
