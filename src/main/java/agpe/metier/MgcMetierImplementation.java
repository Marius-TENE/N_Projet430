package agpe.metier;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agpe.modeles.Piece;
import agpe.modeles.Role;
import agpe.modeles.Utilisateur;
import agpe.repository.RoleRepository;
import agpe.repository.UtilisateurRepository;

@Service
@Transactional
public class MgcMetierImplementation implements AgpeMetier{
	
	@Autowired
	private UtilisateurRepository utr;
	@Autowired
	private RoleRepository ror;

	@Override
	public Utilisateur enregistrerUTilisateur(Utilisateur u) {
		return utr.save(u);
	}

	@Override
	public Utilisateur editerInfosConnexion(Utilisateur u) {
		return utr.save(u);
	}

	@Override
	public Utilisateur editerInfosUtilisateur(Utilisateur u) {
		return utr.save(u);
	}

	@Override
	public Utilisateur editerStatutUtilisateur(Utilisateur u) {
		return utr.save(u);
	}

	@Override
	public Utilisateur recupererCompte(String login, String email) {
		return null;
	}

	@Override
	public boolean chercherUtilisateur(String login) {
		return utr.existsById(login);
	}

	@Override
	public Optional<Utilisateur> chercherUtilisateurAvecLogin(String login) {
		return utr.findById(login);
	}

	@Override
	public Role RetournerRoleUtilisateur(String login) {
		return utr.retournerRole(utr.getOne(login).getMatricule());
	}

	@Override
	public void enregistrerPiece(Utilisateur utilisateur, Piece piece) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimerPiece(Utilisateur utilisateur, Piece piece) {
		// TODO Auto-generated method stub
	}

	@Override
	public Role modifierRole(Utilisateur utilisateur, Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role ajouterRole(Role role) {
		return ror.save(role);
	}

}
