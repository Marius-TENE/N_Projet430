package agpe.chat.modele;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import agpe.chat.modele.chat.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{
	
	@Query("select m from Message m where m.idDestinataire = :u order by m.dateExpedition desc")
	ArrayList<Message> messageRecus(@Param("u")String idUtilisateur);
	@Query("select m from Message m where m.idExpediteur = :u order by m.dateExpedition desc")
	ArrayList<Message> messageEmis(@Param("u")String idUtilisateur);
	@Query("select m from Message m where (m.idExpediteur = :u or m.idDestinataire = :u) order by m.dateExpedition desc")
	ArrayList<Message> tousMesMessages(@Param("u")String idUtilisateur);
	@Query("select count(m.idMessage) from Message m where dateLecture=null and m.idDestinataire = :u")
	int nombreMessageReçusNonLus(@Param("u") String idUtilisateur);
	@Query("select m from Message m where m.idDestinataire = :u and m.dateLecture = null order by m.dateExpedition desc")
	ArrayList<Message> messageNonLus(@Param("u")String idUtilisateur);
}
