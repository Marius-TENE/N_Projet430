package agpe.notification.service;

import java.util.ArrayList;

import agpe.modeles.Utilisateur;
import agpe.notification.modele.Notification;

public interface NotificationService {
	public Notification enregistrerNotification(Notification notification);
	public Notification marquerCommeLu(Notification notification);
	public ArrayList<Notification> notificationsRecus(Utilisateur u);
	public ArrayList<Notification> notificationsNonLus(Utilisateur u);
	
}
