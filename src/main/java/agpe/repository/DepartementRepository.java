package agpe.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import agpe.modeles.Departement;
import agpe.modeles.Etablissement;

public interface DepartementRepository extends JpaRepository<Departement, Integer>{
	
	@Query("select d from Departement d where d.etablissement = :et")
	ArrayList<Departement> ListeDepartementEtablissemnet(@Param("et") Etablissement etablissement);
}
