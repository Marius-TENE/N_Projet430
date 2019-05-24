package agpe.chat.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agpe.chat.modele.Chat;
import agpe.chat.repository.ChatRepository;
import agpe.metier.AgpeMetier;
import agpe.modeles.Departement;
import agpe.modeles.Utilisateur;

@Service
public class ChatServiceImplementation implements ChatService{

	@Autowired
	private ChatRepository chatR;
	
	@Autowired
	AgpeMetier agpeMetier;
	
	@Override
	public Chat enregistrerChat(Chat chat) {
		return chatR.save(chat);
	}

	@Override
	public ArrayList<Chat> listeMessageUtilisateur(Utilisateur user) {
		return chatR.listeMessageUtilisateur(user);
	}

	@Override
	public ArrayList<Chat> listeMessageNonLu(Utilisateur user) {
		return chatR.listeMessageNonLu(user);
	}

	@Override
	public ArrayList<Chat> listeEchangeAvecUtilisateur(Utilisateur user1, Utilisateur user2) {
		return chatR.listeEchangeAvecUtilisateur(user1, user2);
	}

	@Override
	public void envoyerMessageDepartement(Utilisateur user, int idDepartement,String message) {
		ArrayList<Utilisateur> enseignants = agpeMetier.ListerPortfolioParDepartementEtEtablissement(idDepartement);
		int nbre = enseignants.size();
		
		for(int i=0;i<nbre;i++) {
			Chat chat = new Chat(message,new Date(),0,user,enseignants.get(i));
			enregistrerChat(chat);
		}
	}

	@Override
	public void envoyerMessageEtablissement(Utilisateur user, int idEtablissement,String message) {
		ArrayList<Departement> departements = agpeMetier.ListeDepartementEtablissemnet(agpeMetier.chercherEtablissementAvecId(idEtablissement).get());
		int nbre=departements.size();
		
		for(int i=0;i<nbre;i++) {
			envoyerMessageDepartement(user,departements.get(i).getIdDepartement(), message);
		}
	}

	@Override
	public void envoyerMessageAtous(Utilisateur user,String message) {
		ArrayList<Utilisateur> enseignants = agpeMetier.listerTousLesPortfolios();
		int nbre = enseignants.size();
		
		for(int i=0;i<nbre;i++) {
			Chat chat = new Chat(message,new Date(),0,user,enseignants.get(i));
			enregistrerChat(chat);
		}
	}

	@Override
	public ArrayList<Chat> cinqDernierMessages(Utilisateur user) {
		ArrayList<Chat> resultat= new ArrayList<Chat>();
		ArrayList<Chat> tousLesMessages = chatR.listeMessageUtilisateur(user);
		int taille = tousLesMessages.size();
		if(taille<=5) {
			resultat=tousLesMessages;
		}
		else {
			for(int i=0;i<5;i++) {
				resultat.add(tousLesMessages.get(i));
			}
		}
		return resultat ;
	}

	
}
