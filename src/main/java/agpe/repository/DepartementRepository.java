package agpe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import agpe.modeles.Departement;

public interface DepartementRepository extends JpaRepository<Departement, Integer>{
	/*
	 * @Query("select r.nomRole from roles_utilisateur ur, Role r where ur.matricule=:m and r.idRole=ur.idRole"
	 * ) public String retournerRoleUtilisateur(@Param("m")String matricule);
	 */
}
