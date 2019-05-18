package agpe.metier;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import agpe.modeles.Utilisateur;
import agpe.repository.UtilisateurRepository;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{
	
	@Autowired
	private UtilisateurRepository utr;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Utilisateur enregistrerUTilisateur(Utilisateur u) {
		u.setPassword(passwordEncoder.encode(u.getPassword()));
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
	public boolean chercherUtilisateur(String login) {
		return utr.existsById(login);
	}

	@Override
	public Optional<Utilisateur> chercherUtilisateurAvecLogin(String login) {
		return utr.findById(login);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
