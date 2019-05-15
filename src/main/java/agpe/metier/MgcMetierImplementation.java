package agpe.metier;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agpe.mail.MailRequest;
import agpe.mail.MailSenderImplementation;
import agpe.modeles.Piece;
import agpe.modeles.Utilisateur;
import agpe.repository.PieceRepository;
import agpe.repository.UtilisateurRepository;
import agpe.sms.SmsRequest;

@Service
@Transactional
public class MgcMetierImplementation implements AgpeMetier{
	
	@Autowired
	private UtilisateurRepository utr;
	
	@Autowired
	private MailSenderImplementation mls;
	
	@Autowired
	PieceRepository pir;
	
	@Autowired
	private agpe.sms.Service service;

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
	public void enregistrerPiece(Utilisateur utilisateur, Piece piece) {
		// TODO Auto-generated method stub
		
	}

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

}
