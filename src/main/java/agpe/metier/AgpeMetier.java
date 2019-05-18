package agpe.metier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.multipart.MultipartFile;

import agpe.authentification.model.Role;
import agpe.mail.MailRequest;
import agpe.modeles.Categorie;
import agpe.modeles.Departement;
import agpe.modeles.Utilisateur;
import agpe.portfolio.modele.Piece;
import agpe.sms.SmsRequest;

public interface AgpeMetier{
	
	public Utilisateur enregistrerUtilisateur(Utilisateur user);
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
    public String chercherNomPiece(Long fileId);
    public void updatePassword(String password, String login);
    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles);
    
	
}
