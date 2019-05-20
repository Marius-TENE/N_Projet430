package agpe.notification.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agpe.modeles.Utilisateur;
import agpe.notification.modele.Notification;
import agpe.notification.repository.NotificationRepository;

@Service
public class NotificationServiceImplement implements NotificationService{
	
	@Autowired
	private NotificationRepository notificationRepository;

	@Override
	public Notification enregistrerNotification(Notification notification) {
		return notificationRepository.save(notification);
	}

	@Override
	public Notification marquerCommeLu(Notification notification) {
		notification.setDateLecture(new Date());
		notification.setLu(1);
		notificationRepository.save(notification);
		return notification ;
	}

	@Override
	public ArrayList<Notification> notificationsRecus(Utilisateur u) {
		return notificationRepository.notificationsRecus(u.getMatricule());
	}

	@Override
	public ArrayList<Notification> notificationsNonLus(Utilisateur u) {
		return notificationRepository.notificationsNonLus(u.getMatricule());
	}
	
	
}
