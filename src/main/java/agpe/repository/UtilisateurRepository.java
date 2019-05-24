package agpe.repository;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import agpe.modeles.Departement;
import agpe.modeles.Etablissement;
import agpe.modeles.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String>{
	
	@Query("select u from Utilisateur u where u.email = :email")
    Utilisateur findByEmail(@Param("email")String email);

	@Query("select u from Utilisateur u where u.login = :log")
	Utilisateur chercherUtilisateurAvecLogin(@Param("log")String login);
	
    @Modifying
    @Query("update Utilisateur u set u.password = :password where u.matricule = :mat")
    void updatePassword(@Param("password") String password, @Param("mat") String matricule);
    
    @Query("select u from Utilisateur u,Departement d where u.role = enseignant and u.actif = 1 and u.departement = :dp and d=:dp")
    ArrayList<Utilisateur> ListerPortfolioParDepartementEtEtablissement(@Param("dp") Departement departement);
    
    @Query("select u from Utilisateur u where u.role <> 'admin'")
	ArrayList<Utilisateur> listerTousLesPortfolios();
}
