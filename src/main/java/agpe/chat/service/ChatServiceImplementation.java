package agpe.chat.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agpe.chat.modele.Chat;
import agpe.chat.repository.ChatRepository;
import agpe.modeles.Utilisateur;

@Service
public class ChatServiceImplementation implements ChatService{

	@Autowired
	private ChatRepository chatR;
	
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

}
