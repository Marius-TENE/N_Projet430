package agpe.chat.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import agpe.chat.modele.Chat;
import agpe.modeles.Utilisateur;

public interface ChatRepository extends JpaRepository<Chat, Long>{
	
	@Query("select c from Chat c where c.expediteur = :u or c.recepteur = :u order by c.dateEnvoi desc")
	ArrayList<Chat> listeMessageUtilisateur(@Param("u")Utilisateur user);
	
	@Query("select c from Chat c where c.recepteur = :u and c.lu=0")
	ArrayList<Chat> listeMessageNonLu(@Param("u")Utilisateur user);
	
	@Query("select c from Chat c where (c.expediteur = :u1 and c.recepteur = :u2) or (c.expediteur = :u2 and c.recepteur =:u1) order by c.dateEnvoi desc")
	ArrayList<Chat> listeEchangeAvecUtilisateur(@Param("u1")Utilisateur user1,@Param("u2")Utilisateur user2);
	
}
