package agpe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import agpe.modeles.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	  @Query("select r.nomRole from roles_utilisateur ur, Role r where ur.matricule=:m and r.idRole=ur.idRole") 
	  public String retournerRoleUtilisateur(@Param("m")String matricule);
	 
}
