package agpe.notification.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import agpe.modeles.Utilisateur;
import agpe.notification.modele.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>{
	
	@Query("select n from Notification n where n.idUtilisateur = :u or n.idDepositaire = :u order by n.dateNotification desc")
	ArrayList<Notification> notificationsRecus(@Param("u")String idUtilisateur);
	
	@Query("select n from Notification n where (n.idUtilisateur = :u or n.idDepositaire = :u) and n.lu = 0 order by n.dateNotification desc")
	ArrayList<Notification> notificationsNonLus(@Param("u")String idUtilisateur);

}
