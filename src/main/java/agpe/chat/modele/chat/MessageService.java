package agpe.chat.modele.chat;

import java.util.ArrayList;

public interface MessageService {
	public Message enregistrerMessage(Message message);
	public void supprimerMessage(Message message);
	public ArrayList<Message> messageRecus(String idUtilisateur);
	public ArrayList<Message> messageEmis(String idUtilisateur);
	public ArrayList<Message> tousMesMessages(String idUtilisateur);
	public int nombreMessageReçusNonLus(String idUtilisateur);
	public ArrayList<Message> messageNonLus(String idUtilisateur);
}
