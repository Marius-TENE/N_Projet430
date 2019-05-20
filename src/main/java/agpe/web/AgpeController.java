package agpe.web;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import agpe.metier.AgpeMetier;
import agpe.modeles.Categorie;
import agpe.modeles.Utilisateur;
import agpe.portfolio.modele.Piece;
import antlr.collections.List;

@Controller
public class AgpeController {
	
	@Autowired
	private AgpeMetier agpeMetier;
	
	@RequestMapping(value = "/determinerRole",method = RequestMethod.GET)
	public String determinerRoleUtilisateur(HttpSession session) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Utilisateur user = agpeMetier.chercherUtilisateurAvecLogin(auth.getName());
		ArrayList<Categorie> liste_categories = agpeMetier.listeCategoriePieces();
		session.setAttribute("categories",liste_categories);
		session.setAttribute("user", user);

		if(user.getRole().compareToIgnoreCase("enseignant")==0) {
			return "redirect:/enseignant";
		}
		else {
			return "redirect:/admin";
		}

	}
	
	@Secured(value = "ROLE_admin")
	@RequestMapping(value = "/admin",method = RequestMethod.GET)
	public String AfficherInterfaceAccueilAdmi(HttpSession session) {
		
		return "pages/admin";
	}
	
	@Secured(value = {"ROLE_enseignant","ROLE_admin"})
	@RequestMapping(value="/enseignant",method = RequestMethod.GET)
	public ModelAndView AfficherInterfaceAccueilEnseignant(HttpSession httpSession) {

		ModelAndView mav = new ModelAndView();
		mav.clear();
		mav.setViewName("pages/portfolio_enseignant");
		
		ArrayList<Categorie> categ = agpeMetier.listeCategorieNonVideUtilisateur((Utilisateur) httpSession.getAttribute("user"));
		ArrayList<Piece> pieces_ = agpeMetier.listerToutesPiecesUtilisateur((Utilisateur)httpSession.getAttribute("user"));
		
		int nbre_categories  = categ.size();
		int nbre_pieces = pieces_.size();
		Categorie categories[] = new Categorie[nbre_categories];
		Piece pieces[] = new Piece[nbre_pieces];
		
		for(int i=0;i<nbre_pieces;i++) {
			pieces[i]=pieces_.get(i);
		}
		for(int j=0;j<nbre_categories;j++) {
			categories[j]=categ.get(j);
		}
		
		mav.addObject("categories",categories);
		mav.addObject("pieces",pieces);

		return mav;
	}


	@RequestMapping(value = {"/connexion"},method = RequestMethod.GET)
	public String index() {
		return "pages/connexion";
	}
	
	@RequestMapping(value = "/nonAutorise")
	public String nomAutorise() {
		return "pages/nonAutorise";   
	}
	
//	@RequestMapping(value = "/incription",method = RequestMethod.POST)
//	public String getConnexion(String login,String password) {
//		if(login.isEmpty() || password.isEmpty()) {
//			//remplir tous les champs
//			System.out.print("ok " + login+"  "+password);
//		}
//		
//		Optional<Utilisateur> u = mgcMetier.chercherUtilisateurAvecLogin(login);
//		if(u.isPresent()) {
//			if(u.get().getPassword().compareTo(password)==0) {
//				int role=mgcMetier.RetournerRoleUtilisateur(login);
//				
//				if(role==1) {
//					
//					return "pages/inscription_enseignant";
//				}
//				else if(role==2) {
//					return "pages/inscription_sspe";
//				}
//				else {
//					//c'est l'admin
//				}
//			}
//			else {
//				//pass incorrect
//			}
//		}
//		else {
//			// utilisateur introuvable
//		}
//		return "pages/connexion";
//	}
//	
//	@RequestMapping(value = "/enregistrer_sspe",method = RequestMethod.POST)
//	public String SauvergardeMembreSspe(String matricule,String nom,String prenom,String sexe,
//		String email,String mdp,String telephone,String adresse,String date_naissance) {
//			Utilisateur u = new Utilisateur(matricule, nom, prenom, email,matricule, mdp, adresse, telephone,1,"E",2,date_naissance);
//		mgcMetier.enregistrerUTilisateur(u);
//		return "pages/inscription_sspe";
//	}
//	
//	@RequestMapping(value = "/enregistrer_enseignant",method = RequestMethod.POST)
//	public String SauvergarderEnseignant(String matricule,String nom,String prenom,String sexe,String email,String mdp,String telephone,String adresse) {
//		
//		  Utilisateur u = new Utilisateur(matricule, nom, prenom, email, matricule,
//		  mdp, adresse, telephone, 1, "maths","docteur","algebre",1,"15/23/2019");
//		  mgcMetier.enregistrerUTilisateur(u);
//		 
//		return "pages/inscription_enseignant";
//	}
//	
//	  @RequestMapping(value = "/deconnexion") public String Deconnexion(HttpSession
//	  session) { session.invalidate(); return "redirect:pages/connexion";
//	  }
//	 
	
}
