package agpe.chat.service;

import java.util.ArrayList;

import agpe.chat.modele.Chat;
import agpe.modeles.Utilisateur;

public interface ChatService {
	public Chat enregistrerChat(Chat chat);
	public ArrayList<Chat> listeMessageUtilisateur(Utilisateur user);
	public ArrayList<Chat> listeMessageNonLu(Utilisateur user);
	public ArrayList<Chat> listeEchangeAvecUtilisateur(Utilisateur user1,Utilisateur user2);
	public void envoyerMessageDepartement(Utilisateur user,int idDepartement,String message);
	public void envoyerMessageEtablissement(Utilisateur user,int idEtablissement,String message);
	public void envoyerMessageAtous(Utilisateur user,String message);
}
