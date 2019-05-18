package agpe.metier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import agpe.authentification.model.Role;
import agpe.mail.MailRequest;
import agpe.mail.MailSenderImplementation;
import agpe.modeles.Categorie;
import agpe.modeles.Departement;
import agpe.modeles.Utilisateur;
import agpe.portfolio.modele.Piece;
import agpe.portfolio.service.DBFileStorageService;
import agpe.repository.CategorieRepository;
import agpe.repository.DepartementRepository;
import agpe.repository.PieceRepository;
import agpe.repository.UtilisateurRepository;
import agpe.sms.SmsRequest;

@Service
@Transactional
public class MgcMetierImplementation implements AgpeMetier{
	
	@Autowired
	private UtilisateurRepository utr;
	
	
	@Autowired private BCryptPasswordEncoder passwordEncoder;
	 
	@Autowired
	private MailSenderImplementation mls;
	
	@Autowired
	private DBFileStorageService dbfserv;
	
	@Autowired
	private CategorieRepository catr;
	
	@Autowired
	private DepartementRepository departementRepository;
	
	@Autowired
	PieceRepository pir;
	
	@Autowired
	private agpe.sms.Service service;

	@Override
	public void supprimerPiece(Piece piece) {
		pir.delete(piece);
	}

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
	public Piece enregistrerPiece(MultipartFile file, Utilisateur user, Categorie categorie) {
		return dbfserv.storeFile(file, user, categorie);
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
	public void deletePiece(Piece piece) {
		dbfserv.deletePiece(piece);
	}

	@Override
	public int nbrePiecesUtilisateurCategorie(String matricule, int idCategorie) {
		return dbfserv.nbrePiecesUtilisateurCategorie(matricule, idCategorie);
	}

	@Override
	public int nbrePieceUtilisateur(String matricule) {
		return dbfserv.nbrePIeceUtilisateur(matricule);
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
	public void updatePassword(String password, String login) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur enregistrerUtilisateur(Utilisateur user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return utr.save(user);
	}
	

}
