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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import agpe.chat.modele.Chat;
import agpe.metier.AgpeMetier;
import agpe.modeles.Categorie;
import agpe.modeles.Departement;
import agpe.modeles.Etablissement;
import agpe.modeles.Utilisateur;
import agpe.notification.modele.Notification;
import agpe.portfolio.modele.Piece;
import agpe.statistiques.model.Statistique;

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
	
	public String extraireAnneeNaissance(String date /* la date doit être sous le format AAAA-MM-JJ*/) {
		char ext[]=new char[4];
		date.getChars(0,3,ext,0);
		return new String(ext);
	}
	
	public ArrayList<ArrayList<String>> statNombrePortfolioParDepartement(int codeEtablissement) {
		ArrayList<ArrayList<String>> liste_finale = new ArrayList<ArrayList<String>>();
		ArrayList<Departement> depart = agpeMetier.ListeDepartementEtablissemnet(agpeMetier.chercherEtablissementAvecId(codeEtablissement).get());
		ArrayList<String> label = new ArrayList<String>();
		ArrayList<String> y = new ArrayList<String>();
		
		ArrayList<Utilisateur> tmp = new ArrayList<Utilisateur>();
		int nbre=depart.size();
		for(int i=0;i<nbre;i++) {
			label.add(depart.get(i).getNomDepartement());
			tmp=agpeMetier.ListerPortfolioParDepartementEtEtablissement(depart.get(i).getIdDepartement());
			y.add(String.valueOf(tmp.size()).toString());
		}
		liste_finale.add(label);
		liste_finale.add(y);
		return liste_finale;
	}
	
	public ArrayList<ArrayList<String>> statNombrePortfolioParEtablissement(){
		ArrayList<ArrayList<String>> liste_finale = new ArrayList<ArrayList<String>>();
		ArrayList<Etablissement> etab = agpeMetier.listeEtablissement();
		ArrayList<String> label = new ArrayList<String>();
		ArrayList<String> y = new ArrayList<String>();
		
		ArrayList<Utilisateur> tmp = new ArrayList<Utilisateur>();
		int nbre=etab.size();
		for(int i=0;i<nbre;i++) {
			label.add(etab.get(i).getNomEtablissement());
			tmp = agpeMetier.ListerPortfolioParEtablissement(etab.get(i).getIdEtablissement());
			y.add(String.valueOf(tmp.size()).toString());
		}
		liste_finale.add(label);
		liste_finale.add(y);
		return liste_finale;
	}
	
	
	
	
	@Secured(value = "ROLE_admin")
	@RequestMapping(value = "/admin",method = RequestMethod.GET)
	public ModelAndView AfficherInterfaceAccueilAdmi(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.clear();
		mav.setViewName("pages/admin");
		
		ArrayList<Etablissement> etablissements = agpeMetier.listeEtablissement();
		ArrayList<Departement> departements = agpeMetier.listerTousLesDepartements();
		ArrayList<Utilisateur> portfolios = agpeMetier.listerTousLesPortfolios();
		
		ArrayList<Statistique> statistiques = new ArrayList<Statistique>();
		ArrayList<Statistique> statistiques1 = new ArrayList<Statistique>();
		
		int nbre_etab=etablissements.size();
		int nbre_depart = departements.size();
		int nbre_port = portfolios.size();
		
		for(int i=0;i<nbre_etab;i++) {
			int nombre_occurences=0;
			int nombre_occ=0;//stat1
			for(int k=0;k<nbre_depart;k++) {
				
				if(etablissements.get(i).getIdEtablissement()==departements.get(k).getIdDepartement()) {
					nombre_occ++;
				}
				//statistique
				for(int j=0;j<nbre_port;j++) {
					if(etablissements.get(i).getIdEtablissement()==departements.get(k).getEtablissement().getIdEtablissement()
							&& departements.get(k).getIdDepartement()==portfolios.get(j).getDepartement().getIdDepartement()) {
						nombre_occurences++;
					}
				}
			}
			Statistique stat = new Statistique(etablissements.get(i),nombre_occurences);
			statistiques.add(stat);
			Statistique stat1 = new Statistique(etablissements.get(i),nombre_occ);
			statistiques1.add(stat1);
			System.out.print(stat.toString());
		}
		
		/*
		 * Histogramme histogramme = genererHistogramme(statistiques,
		 * "column","Nombre de portfolios gérés par établissement");
		 * mav.addObject("statistiques",statistiques); Histogramme histogramme1 =
		 * genererHistogramme(statistiques1,
		 * "column","Nombre de departements par établissement");
		 * mav.addObject("histogramme",histogramme);
		 */
		ArrayList<String> liste = new ArrayList<String>();
		liste.add("bonjour");
		liste.add("Monsieur");
		mav.addObject("liste",liste);
		
		return mav;
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
		ArrayList<Chat> messageNonsLus = agpeMetier.listeMessageNonLu(user);
		ArrayList<Chat> dernierMessages = agpeMetier.cinqDernierMessages(user);
		int nbre_message_non_lus = messageNonsLus.size();
		
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
		mav.addObject("messages", dernierMessages);
		mav.addObject("nbre_messages", nbre_message_non_lus);
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
		System.out.print("ok \n\n" + etablissements.get(0).toString()+"\n\n\n");
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
	
	@GetMapping("/admin/portfolio_{matricule}")
    public ModelAndView accederPorfolioEnseignant(@PathVariable String matricule) {
      
        ModelAndView mav = new ModelAndView();
        mav.clear();
        mav.setViewName("/pages/consulter_portfolio_admin");
        Utilisateur user = agpeMetier.chercherUtiliateurAvecMatricule(matricule).get();
        ArrayList<Categorie> categories = agpeMetier.listeCategorieNonVideUtilisateur(user);
		ArrayList<Piece> pieces = agpeMetier.listerToutesPiecesUtilisateur(user);
		mav.addObject("nbre_pieces", agpeMetier.nbrePieceUtilisateur(user));
		mav.addObject("categories",categories);
		mav.addObject("user",user);
		mav.addObject("pieces",pieces);
		
        return mav;
    }
	
	@GetMapping("/nouveau_message_{matricule}")
	public ModelAndView AfficherListeDestinataires(@PathVariable String matricule) {
		ModelAndView mav = new ModelAndView();
		mav.clear();
		return mav;
	}
    
}