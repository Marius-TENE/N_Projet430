package agpe.repository;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import agpe.modeles.Categorie;
import agpe.modeles.Utilisateur;
import agpe.portfolio.modele.Piece;


public interface PieceRepository extends JpaRepository<Piece, Long>{
	
	@Query("select p from Piece p,Utilisateur u,Categorie c where p.categorie=:cat and p.utilisateur=:mat order by p.date_ajout asc")
	public Collection<Piece> chercherPieceUtilisateurAvecCategorie(@Param("mat")String matricule,@Param("cat")int idCategorie);
	
	@Query("select count(p) from Piece p where p.categorie=:cat and p.utilisateur=:u")
	public int nbrePiecesUtilisateurCategorie(@Param("u")Utilisateur user,@Param("cat")Categorie categorie);
	
	@Query("select count(p) from Piece p where p.utilisateur=:u")
	public int nbrePiecesUtilisateur(@Param("u")Utilisateur user);
	
	@Query("select distinct p.categorie from Piece p where p.utilisateur = :u")
	public ArrayList<Categorie> listeCategorieNonVideUtilisateur(@Param("u")Utilisateur u);
	
	@Query("select p from Piece p where p.utilisateur = :u")
	public ArrayList<Piece> listerToutesPiecesUtilisateur(Utilisateur u);
	
	@Query("select c from Categorie c")
	public ArrayList<Categorie> listeCategoriePieces();
}
