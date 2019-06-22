package agpe.metier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import agpe.authentification.model.PasswordResetToken;
import agpe.authentification.repository.PasswordResetTokenRepository;
import agpe.chat.modele.Chat;
import agpe.chat.service.ChatService;
import agpe.mail.MailRequest;
import agpe.mail.MailSenderImplementation;
import agpe.modeles.Categorie;
import agpe.modeles.Departement;
import agpe.modeles.Etablissement;
import agpe.modeles.Utilisateur;
import agpe.notification.modele.Notification;
import agpe.notification.service.NotificationServiceImplement;
import agpe.portfolio.modele.Piece;
import agpe.portfolio.service.DBFileStorageService;
import agpe.repository.CategorieRepository;
import agpe.repository.DepartementRepository;
import agpe.repository.EtablissementRepository;
import agpe.repository.PieceRepository;
import agpe.repository.UtilisateurRepository;
import agpe.sms.SmsRequest;
import agpe.statistiques.model.Statistique;

@Service
@Transactional
public class MgcMetierImplementation implements AgpeMetier{
	
	@Autowired
	private ChatService chatServ;
	
	@Autowired
	private UtilisateurRepository utr;
	
	@Autowired
	private NotificationServiceImplement notServ;
	
	@Autowired
	private PasswordResetTokenRepository tokenR;
	
	@Autowired 
	private BCryptPasswordEncoder passwordEncoder;
	 
	@Autowired
	private MailSenderImplementation mls;
	
	@Autowired
	private DBFileStorageService dbfserv;
	
	@Autowired
	private CategorieRepository catr;
	
	@Autowired
	private DepartementRepository departementRepository;
	
	@Autowired
	private EtablissementRepository etR;
	
	@Autowired
	PieceRepository pir;
	
	@Autowired
	private agpe.sms.Service service;

	@Override
	public void envoyerSms(SmsRequest smsRequest) {
		service.envoyerSms(smsRequest);
	}

	@Override
	public void envoyerSmsGroup(ArrayList<Utilisateur> users, String message) {
		int nbre_user= users.size();
		for(int i=0;i<nbre_user;i++) {
			SmsRequest smsRequest = new SmsRequest(users.get(i).getTel(), message);
			try {
				envoyerSms(smsRequest);
			} catch (Exception e) {
				//une erreur lors de l'envoi
			}
			
		}
	}

	@Override
	public void envoyerMail(MailRequest mailRequest) {
		try {
			mls.envoyerMail(mailRequest);
		} catch (Exception e) {
			System.out.print("Une erreur : "+e.getMessage()); 
		}
	
	}

	@Override
	public void envoyerMailGroup(ArrayList<Utilisateur> users, String message, String objet) {
		int nbre_user = users.size();
		for(int i=0;i<nbre_user;i++) {
			MailRequest mailRequest = new MailRequest(users.get(i).getEmail(),message,objet);
			mls.envoyerMail(mailRequest);
		}
	}

	@Override
	public Piece enregistrerPiece(MultipartFile file, Utilisateur user, Categorie categorie,String nouveauNom,String idDepositaire) {
		return dbfserv.storeFile(file, user, categorie,nouveauNom,idDepositaire);
	}

	@Override
	public Piece chercherPiece(Long idPiece) {
		return dbfserv.getFile(idPiece);
	}

	@Override
	public Collection<Piece> chercherPieceUtilisateurAvecCategorie(String matricule, int idCategorie) {
		return dbfserv.chercherPieceUtilisateurAvecCategorie(matricule, idCategorie);
	}

	@Override
	public void deletePiece(Piece piece,String idAuteur) {
		dbfserv.deletePiece(piece,idAuteur);
	}

	@Override
	public int nbrePiecesUtilisateurCategorie(Utilisateur user,Categorie categorie) {
		return dbfserv.nbrePiecesUtilisateurCategorie(user, categorie);
	}

	@Override
	public int nbrePieceUtilisateur(Utilisateur user) {
		return dbfserv.nbrePIeceUtilisateur(user);
	}

	@Override
	public void enregistrerCategorie(Categorie categorie) {
		catr.save(categorie);
	}

	@Override
	public void enregistrerDepartement(Departement depart) {
		departementRepository.save(depart);
	}

	@Override
	public Optional<Categorie> retournerCategorie(int idCategorie) {
		return catr.findById(idCategorie);
	}

	@Override
	public String chercherNomPiece(Long fileId) {
		return dbfserv.chercherNomPiece(fileId);
	}

	@Override
	public Utilisateur enregistrerUtilisateur(Utilisateur user) {
		//service.envoyerSms(new SmsRequest(user.getTel(),"Votre portfolio vient d'être crée.\nVos informations de connexion sont les suivantes:\nLogin: "+user.getLogin()+"\npassword: "+user.getPassword()));
		mls.envoyerMail(new MailRequest(user.getEmail(),"Votre portfolio vient d'être crée.\nVos informations de connexion sont les suivantes:\nLogin: "+user.getLogin()+"\npassword: "+user.getPassword()));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return utr.save(user);
	}

	@Override
	public Utilisateur chercherUtilisateurAvecEmail(String email) {
		Utilisateur u = utr.findByEmail(email);
		return u;
	}

	@Override
	public void ModifierMotPasse(String password, String matricule,String telephone) {
		SmsRequest sms = new SmsRequest(telephone,"Mot de passe changé avec succès !.\nNouveau mot de passe: \n"+password);
		password=passwordEncoder.encode(password);
		utr.updatePassword(password, matricule);
		Utilisateur user = utr.findById(matricule).get();
		service.envoyerSms(sms);
		mls.envoyerMail(new MailRequest(user.getEmail(),"Mot de passe changé avec succès !.\nNouveau mot de passe: \n"+password));
	}

	@Override
	public PasswordResetToken enregisterToken(PasswordResetToken token) {
		return tokenR.save(token);
	}

	@Override
	public PasswordResetToken findByToken(String token) {
		return tokenR.findByToken(token);
	}

	@Override
	public void supprimerToken(PasswordResetToken token) {
		tokenR.delete(token);
	}

	@Override
	public String retournerRoleUtilisateur(String login) {
		Utilisateur u = utr.findById(login).get();
		return u.getRole();
	}

	@Override
	public Utilisateur chercherUtilisateurAvecLogin(String login) {
		return utr.chercherUtilisateurAvecLogin(login);
	}

	@Override
	public ArrayList<Utilisateur> ListerPortfolioParDepartementEtEtablissement(int idDepartement) {
		Departement departement = departementRepository.findById(idDepartement).get();
		return utr.ListerPortfolioParDepartementEtEtablissement(departement);
	}

	@Override
	public ArrayList<Utilisateur> ListerPortfolioParEtablissement(int idEtablissement) {
		ArrayList<Utilisateur> liste = new ArrayList<Utilisateur>();
		ArrayList<Departement> liste_departement = departementRepository.ListeDepartementEtablissemnet(etR.findById(idEtablissement).get()); 
		int nbre_depart = liste_departement.size();
		
		for(int i=0;i<nbre_depart;i++) {
			liste.addAll(ListerPortfolioParDepartementEtEtablissement(liste_departement.get(i).getIdDepartement()));
		}
		return liste;
	}

	@Override
	public Etablissement ajouterEtablissement(Etablissement etablissment) {
		return etR.save(etablissment);
	}

	@Override
	public ArrayList<Departement> ListeDepartementEtablissemnet(Etablissement etablissement) {
		return departementRepository.ListeDepartementEtablissemnet(etablissement);
	}

	@Override
	public Utilisateur modifierInfosConnexion(Utilisateur user) {
		Utilisateur user1 =enregistrerUtilisateur(user);
		SmsRequest sms = new SmsRequest(user.getTel(),"Vos nouvelles informations de connexion sont les suivantes: \nLogin: "+user.getLogin()+ "\nPasswor: "+user.getPassword()); 
		envoyerSms(sms);
		envoyerMail(new MailRequest(user.getEmail(),"Vos nouvelles informations de connexion sont les suivantes: \nLogin: "+user.getLogin()+ "\nPasswor: "+user.getPassword()));
		return user1;
	}

	@Override
	public Categorie rechercherCategoriePieceAvecNom(String nomCategorie) {
		return catr.rechercherIdCategoriePieceAvecNom(nomCategorie);
	}

	@Override
	public ArrayList<Categorie> listeCategoriePieces() {
		return pir.listeCategoriePieces();
	}

	@Override
	public ArrayList<Categorie> listeCategorieNonVideUtilisateur(Utilisateur u) {
		return pir.listeCategorieNonVideUtilisateur(u);
	}

	@Override
	public ArrayList<Piece> listerToutesPiecesUtilisateur(Utilisateur u) {
		return dbfserv.listerToutesPiecesUtilisateur(u);
	}

	@Override
	public Notification enregistrerNotification(Notification notification) {
		return notServ.enregistrerNotification(notification);
	}

	@Override
	public Notification marquerCommeLu(Notification notification) {
		return notServ.marquerCommeLu(notification);
	}

	@Override
	public ArrayList<Notification> notificationsRecus(Utilisateur u) {
		return notServ.notificationsRecus(u);
	}

	@Override
	public ArrayList<Notification> notificationsNonLus(Utilisateur u) {
		return notServ.notificationsNonLus(u);
	}

	@Override
	public ArrayList<Etablissement> listeEtablissement() {
		return etR.listeEtablissement();
	}

	@Override
	public ArrayList<Departement> listerTousLesDepartements() {
		return departementRepository.listerTousLesDepartements();
	}

	@Override
	public ArrayList<Utilisateur> listerTousLesPortfolios() {
		return utr.listerTousLesPortfolios();
	}

	@Override
	public Etablissement enregistrerEtablissement(Etablissement etablissement) {
		return etR.save(etablissement);
	}

	@Override
	public Chat enregistrerChat(Chat chat) {
		return chatServ.enregistrerChat(chat);
	}

	@Override
	public ArrayList<Chat> listeMessageUtilisateur(Utilisateur user) {
		return chatServ.listeMessageUtilisateur(user);
	}

	@Override
	public ArrayList<Chat> listeMessageNonLu(Utilisateur user) {
		return chatServ.listeMessageNonLu(user);
	}

	@Override
	public ArrayList<Chat> listeEchangeAvecUtilisateur(Utilisateur user1, Utilisateur user2) {
		return chatServ.listeEchangeAvecUtilisateur(user1, user2);
	}

	@Override
	public Optional<Etablissement> chercherEtablissementAvecId(int idEtablissement) {
		return etR.findById(idEtablissement);
	}

	@Override
	public Optional<Utilisateur> chercherUtiliateurAvecMatricule(String matricule) {
		return utr.findById(matricule);
	}

	@Override
	public Departement chercherDepartementAvecId(int idDepartement) {
		return departementRepository.findById(idDepartement).get();
	}

	@Override
	public ArrayList<Chat> cinqDernierMessages(Utilisateur user) {
		return chatServ.cinqDernierMessages(user);
	}

	@Override
	public Boolean existanteUtilisateur(String matricule) {
		return utr.existsById(matricule);
	}

}
