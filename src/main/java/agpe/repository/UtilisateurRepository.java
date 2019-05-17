package agpe.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import agpe.modeles.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String>{

    Utilisateur findByEmail(String email);

    @Modifying
    @Query("update Utilisateur u set u.password = :password where u.matricule = :mat")
    void updatePassword(@Param("password") String password, @Param("mat") String matricule);
}
