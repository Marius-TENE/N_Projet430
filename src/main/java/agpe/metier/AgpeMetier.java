package agpe.metier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import agpe.mail.MailRequest;
import agpe.modeles.Categorie;
import agpe.modeles.Departement;
import agpe.modeles.Utilisateur;
import agpe.portfolio.modele.Piece;
import agpe.sms.SmsRequest;

public interface AgpeMetier{
	public Utilisateur enregistrerUTilisateur(Utilisateur u);
	public Utilisateur editerInfosConnexion(Utilisateur u);
	public Utilisateur editerInfosUtilisateur(Utilisateur u);
	public Utilisateur editerStatutUtilisateur(Utilisateur u);
	public Optional<Utilisateur> chercherUtilisateurAvecLogin(String login);
	public Utilisateur recupererCompte(String login,String email);
	public boolean chercherUtilisateur(String login);
	public void supprimerPiece(Piece piece);
	public void envoyerSms(SmsRequest smsRequest);
	public void envoyerSmsGroup(ArrayList<Utilisateur> users,String message);
	public void envoyerMail(MailRequest mailRequest);
	public void envoyerMailGroup(ArrayList<Utilisateur> users,String message,String objet);
    public Piece enregistrerPiece(MultipartFile file,Utilisateur user,Categorie categorie);
    public Piece chercherPiece(Long idPiece);
    public Collection<Piece> chercherPieceUtilisateurAvecCategorie(String matricule,int idCategorie);
    public void deletePiece(Piece piece);
    public int nbrePiecesUtilisateurCategorie(String matricule,int idCategorie);
    public int nbrePieceUtilisateur(String matricule);
    public void enregistrerCategorie(Categorie categorie);
    public void enregistrerDepartement(Departement depart);
    public Optional<Categorie> retournerCategorie(int idCategorie);
    
	
}
