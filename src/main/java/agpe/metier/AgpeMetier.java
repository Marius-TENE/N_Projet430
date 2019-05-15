package agpe.metier;

import java.util.Optional;

import org.springframework.data.domain.Page;

import agpe.modeles.Piece;
import agpe.modeles.Role;
import agpe.modeles.Utilisateur;

public interface AgpeMetier{
	public Utilisateur enregistrerUTilisateur(Utilisateur u);
	public Utilisateur editerInfosConnexion(Utilisateur u);
	public Utilisateur editerInfosUtilisateur(Utilisateur u);
	public Utilisateur editerStatutUtilisateur(Utilisateur u);
	public Optional<Utilisateur> chercherUtilisateurAvecLogin(String login);
	public Utilisateur recupererCompte(String login,String email);
	public boolean chercherUtilisateur(String login);
	public Role RetournerRoleUtilisateur(String login);
	public void enregistrerPiece(Utilisateur utilisateur,Piece piece);
	public void supprimerPiece(Utilisateur utilisateur,Piece piece);
	public Role modifierRole(Utilisateur utilisateur,Role role);
	public Role ajouterRole(Role role);
	
}
