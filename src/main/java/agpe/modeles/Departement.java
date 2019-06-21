package agpe.modeles;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Departement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDepartement;
	
	@NotNull
	private String nomDepartement;
	
	@ManyToOne
	@JoinColumn(name = "idEtablissement")
	private Etablissement etablissement;
	
	@OneToMany(mappedBy = "departement",fetch = FetchType.LAZY)
	private Collection<Utilisateur> enseignants;

	public Departement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Departement(@NotNull String nomDepartement) {
		super();
		this.nomDepartement = nomDepartement;
	}
	
	

	public Departement(@NotNull String nomDepartement, Etablissement etablissement) {
		super();
		this.nomDepartement = nomDepartement;
		this.etablissement = etablissement;
	}

	public int getIdDepartement() {
		return idDepartement;
	}

	public void setIdDepartement(int idDepartement) {
		this.idDepartement = idDepartement;
	}

	public String getNomDepartement() {
		return nomDepartement;
	}

	public void setNomDepartement(String nomDepartement) {
		this.nomDepartement = nomDepartement;
	}

	public Etablissement getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(Etablissement etablissement) {
		this.etablissement = etablissement;
	}

	public Collection<Utilisateur> getEnseignants() {
		return enseignants;
	}

	public void setEnseignants(Collection<Utilisateur> enseignants) {
		this.enseignants = enseignants;
	}

	@Override
	public String toString() {
		return "Departement [idDepartement=" + idDepartement + ", nomDepartement=" + nomDepartement + "]";
	}
	
	
}
