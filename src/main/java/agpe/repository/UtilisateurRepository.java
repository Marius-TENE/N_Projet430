package agpe.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import agpe.modeles.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String>{
	/*
	 * @Query("select r from Utilisateur u, Role r where u.matricule=:m and r.idRole=u.idRole"
	 * ) public Role retournerRole(@Param("m")String matricule);
	 */
}
