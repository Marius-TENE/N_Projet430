package agpe.metier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import agpe.authentification.model.PasswordResetToken;
import agpe.chat.modele.Chat;
import agpe.mail.MailRequest;
import agpe.modeles.Categorie;
import agpe.modeles.Departement;
import agpe.modeles.Etablissement;
import agpe.modeles.Utilisateur;
import agpe.notification.modele.Notification;
import agpe.portfolio.modele.Piece;
import agpe.sms.SmsRequest;
import agpe.statistiques.model.Statistique;

public interface AgpeMetier{
	
	public Utilisateur enregistrerUtilisateur(Utilisateur user);
	public Utilisateur chercherUtilisateurAvecEmail(String email);
	public Utilisateur chercherUtilisateurAvecLogin(String login);
	public Boolean existanteUtilisateur(String matricule);
	public String retournerRoleUtilisateur(String login);
	public void ModifierMotPasse(String password,String matricule, String telephone);
	public Utilisateur modifierInfosConnexion(Utilisateur user);
	public ArrayList<Categorie> listeCategoriePieces();
	public ArrayList<Categorie> listeCategorieNonVideUtilisateur(Utilisateur u);
	public ArrayList<Utilisateur> ListerPortfolioParDepartementEtEtablissement(int idDepartement);
	public ArrayList<Utilisateur> ListerPortfolioParEtablissement(int idEtablissement);
	public void envoyerSms(SmsRequest smsRequest);
	public void envoyerSmsGroup(ArrayList<Utilisateur> users,String message);
	public void envoyerMail(MailRequest mailRequest);
	public void envoyerMailGroup(ArrayList<Utilisateur> users,String message,String objet);
    public Piece enregistrerPiece(MultipartFile file,Utilisateur user,Categorie categorie, String nouveauNom,String idDepositaire);
    public Piece chercherPiece(Long idPiece);
    public Collection<Piece> chercherPieceUtilisateurAvecCategorie(String matricule,int idCategorie);
    public void deletePiece(Piece piece,String idAuteur);
    public int nbrePiecesUtilisateurCategorie(Utilisateur user,Categorie categorie);
    public int nbrePieceUtilisateur(Utilisateur user);
    public void enregistrerCategorie(Categorie categorie);
    public void enregistrerDepartement(Departement depart);
    public Optional<Categorie> retournerCategorie(int idCategorie);
    public String chercherNomPiece(Long fileId);
    public PasswordResetToken enregisterToken(PasswordResetToken token);
    public PasswordResetToken findByToken(String token);
    public void supprimerToken(PasswordResetToken token);
    public Categorie rechercherCategoriePieceAvecNom(String nomCategorie);
    public Etablissement ajouterEtablissement(Etablissement etablissment);
    public ArrayList<Etablissement> listeEtablissement();
    public ArrayList<Departement> ListeDepartementEtablissemnet(Etablissement etablissement);
    public ArrayList<Departement> listerTousLesDepartements();
    public ArrayList<Utilisateur> listerTousLesPortfolios();
    public ArrayList<Piece> listerToutesPiecesUtilisateur(Utilisateur u);
    public Notification enregistrerNotification(Notification notification);
	public Notification marquerCommeLu(Notification notification);
	public ArrayList<Notification> notificationsRecus(Utilisateur u);
	public ArrayList<Notification> notificationsNonLus(Utilisateur u);
	public Etablissement enregistrerEtablissement(Etablissement etablissement);
	public Chat enregistrerChat(Chat chat);
	public ArrayList<Chat> listeMessageUtilisateur(Utilisateur user);
	public ArrayList<Chat> listeMessageNonLu(Utilisateur user);
	public ArrayList<Chat> listeEchangeAvecUtilisateur(Utilisateur user1,Utilisateur user2);
	public Optional<Etablissement> chercherEtablissementAvecId(int idEtablissement);
	public Optional<Utilisateur> chercherUtiliateurAvecMatricule(String matricule);
	public Departement chercherDepartementAvecId(int idDepartement);
	public ArrayList<Chat> cinqDernierMessages(Utilisateur user);
	
}
