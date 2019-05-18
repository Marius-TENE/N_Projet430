package agpe.authentification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import agpe.authentification.model.PasswordResetToken;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
	
	@Query("select t from PasswordResetToken t where t.token = :tok")
    PasswordResetToken findByToken(@Param("tok")String token);

}
