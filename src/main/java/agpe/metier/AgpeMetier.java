package agpe.metier;

import java.util.ArrayList;
import java.util.Optional;

import agpe.mail.MailRequest;
import agpe.modeles.Piece;
import agpe.modeles.Utilisateur;
import agpe.sms.SmsRequest;

public interface AgpeMetier{
	public Utilisateur enregistrerUTilisateur(Utilisateur u);
	public Utilisateur editerInfosConnexion(Utilisateur u);
	public Utilisateur editerInfosUtilisateur(Utilisateur u);
	public Utilisateur editerStatutUtilisateur(Utilisateur u);
	public Optional<Utilisateur> chercherUtilisateurAvecLogin(String login);
	public Utilisateur recupererCompte(String login,String email);
	public boolean chercherUtilisateur(String login);
	public void enregistrerPiece(Utilisateur utilisateur,Piece piece);
	public void supprimerPiece(Piece piece);
	public void envoyerSms(SmsRequest smsRequest);
	public void envoyerSmsGroup(ArrayList<Utilisateur> users,String message);
	public void envoyerMail(MailRequest mailRequest);
	public void envoyerMailGroup(ArrayList<Utilisateur> users,String message,String objet);
	
}
