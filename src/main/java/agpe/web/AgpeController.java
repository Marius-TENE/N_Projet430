package agpe.web;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import agpe.metier.AgpeMetier;
import agpe.modeles.Categorie;
import agpe.modeles.Departement;
import agpe.modeles.Etablissement;
import agpe.modeles.Utilisateur;
import agpe.notification.modele.Notification;
import agpe.portfolio.modele.Piece;

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
	
	@Secured(value = {"ROLE_enseignant"})
	@RequestMapping(value="/enseignant",method = RequestMethod.GET)
	public ModelAndView AfficherInterfaceAccueilEnseignant(HttpSession httpSession) {

		ModelAndView mav = new ModelAndView();
		mav.clear();
		mav.setViewName("pages/portfolio_enseignant");
		Utilisateur user = (Utilisateur) httpSession.getAttribute("user");
		ArrayList<Categorie> categories = agpeMetier.listeCategorieNonVideUtilisateur(user);
		ArrayList<Piece> pieces = agpeMetier.listerToutesPiecesUtilisateur(user);
		
		ArrayList<Notification> dernierenotification = agpeMetier.notificationsRecus(user);
		ArrayList<Notification> notificationNonLu = agpeMetier.notificationsNonLus(user);
		int nbre_notification_non_lu = notificationNonLu.size();
		System.out.print("ok \n\n" + nbre_notification_non_lu+"\n\n\n");
		ArrayList<Notification> cinqdernieresNotification = new ArrayList<Notification>();
		if(dernierenotification.size()>5) {
			for(int i=0;i<5;i++) {
				cinqdernieresNotification.add(dernierenotification.get(i));
			}
		}
		else {
			cinqdernieresNotification=dernierenotification;
		}
		mav.addObject("nbre_pieces", agpeMetier.nbrePieceUtilisateur(user));
		mav.addObject("notifications",cinqdernieresNotification);
		mav.addObject("nbre_notif", nbre_notification_non_lu);
		mav.addObject("categories",categories);
		mav.addObject("nbre_notifs", cinqdernieresNotification.size());
		mav.addObject("pieces",pieces);

		return mav;
	}
	
	
	@Secured(value = {"ROLE_admin"})
	@RequestMapping(value = "/consultation_portfolios_enseignants",method = RequestMethod.GET)
	public ModelAndView ConsulterPortfoliosEnseignants(HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		mav.clear();
		mav.setViewName("pages/afficher_portfolios");
		ArrayList<Etablissement> etablissements = agpeMetier.listeEtablissement();
		ArrayList<Departement> departements = agpeMetier.listerTousLesDepartements();
		ArrayList<Utilisateur> portfolios = agpeMetier.listerTousLesPortfolios();
		mav.addObject("etablissements", etablissements);
		mav.addObject("departements", departements);
		mav.addObject("portfolios", portfolios);
		return mav;
	}
	
	@RequestMapping(value = "/notification",method = RequestMethod.GET)
	public ModelAndView AfficherNotificationComplete(HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		mav.clear();
		mav.setViewName("pages/notification");
		Utilisateur user = (Utilisateur) httpSession.getAttribute("user");
		ArrayList<Notification> dernierenotification = agpeMetier.notificationsRecus(user);
		ArrayList<Notification> notificationNonLu = agpeMetier.notificationsNonLus(user);
		int nbre=notificationNonLu.size();
		for(int i=0;i<nbre;i++) {
			notificationNonLu.get(i).setLu(1);
			notificationNonLu.get(i).setDateLecture(new Date());
			agpeMetier.marquerCommeLu(notificationNonLu.get(i));
		}
		System.out.print("ok \n\n" + dernierenotification.get(0).toString()+"\n\n\n");
		System.out.print("ok \n\n" + nbre+"\n\n\n");
		mav.addObject("notifications",dernierenotification);
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
