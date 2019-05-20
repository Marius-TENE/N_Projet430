package agpe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import agpe.modeles.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Integer>{
	
	@Query("select c from Categorie c where c.nomCategorie = :cat")
	Categorie rechercherIdCategoriePieceAvecNom(@Param("cat")String nomCategorie);

}
