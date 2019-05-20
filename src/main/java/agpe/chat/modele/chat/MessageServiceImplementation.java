package agpe.chat.modele.chat;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agpe.chat.modele.MessageRepository;

@Service
public class MessageServiceImplementation implements MessageService {
	
	@Autowired
	private MessageRepository messageRepository;

	@Override
	public Message enregistrerMessage(Message message) {
		return messageRepository.save(message);
	}

	@Override
	public void supprimerMessage(Message message) {
		 messageRepository.delete(message);
	}

	@Override
	public ArrayList<Message> messageRecus(String idUtilisateur) {
		return messageRepository.messageRecus(idUtilisateur);
	}

	@Override
	public ArrayList<Message> messageEmis(String idUtilisateur) {
		return messageRepository.messageEmis(idUtilisateur);
	}

	@Override
	public ArrayList<Message> tousMesMessages(String idUtilisateur) {
		return messageRepository.tousMesMessages(idUtilisateur);
	}

	@Override
	public int nombreMessageReçusNonLus(String idUtilisateur) {
		return messageRepository.nombreMessageReçusNonLus(idUtilisateur);
	}

	@Override
	public ArrayList<Message> messageNonLus(String idUtilisateur) {
		return messageRepository.messageNonLus(idUtilisateur);
	}
	
	
}
