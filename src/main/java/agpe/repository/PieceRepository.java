package agpe.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import agpe.portfolio.modele.Piece;


public interface PieceRepository extends JpaRepository<Piece, Long>{
	
	@Query("select p from Piece p,Utilisateur u,Categorie c where p.categorie=:cat and p.utilisateur=:mat order by p.date_ajout asc")
	public Collection<Piece> chercherPieceUtilisateurAvecCategorie(@Param("mat")String matricule,@Param("cat")int idCategorie);
	
	@Query("select count(p) from Piece p,Utilisateur u,Categorie c where p.categorie=:cat and p.utilisateur=:mat")
	public int nbrePiecesUtilisateurCategorie(@Param("mat")String matricule,@Param("cat")int idCategorie);
	
	@Query("select count(p) from Piece p,Utilisateur u where p.utilisateur=:mat")
	public int nbrePiecesUtilisateur(@Param("mat")String matricule);

}
