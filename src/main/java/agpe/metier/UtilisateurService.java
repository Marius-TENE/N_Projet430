package agpe.metier;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import agpe.modeles.Utilisateur;

public interface UtilisateurService extends UserDetailsService{
	
	public Utilisateur enregistrerUTilisateur(Utilisateur u);
	public Utilisateur editerInfosConnexion(Utilisateur u);
	public Utilisateur editerInfosUtilisateur(Utilisateur u);
	public Utilisateur editerStatutUtilisateur(Utilisateur u);
	public Optional<Utilisateur> chercherUtilisateurAvecLogin(String login);
	public boolean chercherUtilisateur(String login);
}
