package agpe.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import agpe.modeles.Etablissement;

public interface EtablissementRepository extends JpaRepository<Etablissement, Integer>{
	
	@Query("select e from Etablissement e")
	ArrayList<Etablissement> listeEtablissement();

}
