package agpe.modeles;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Etablissement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEtablissement;
	
	@NotNull
	private String nomEtablissement;
	
	@NotNull
	private String adresseEtablissement;
	
	@NotNull
	private String telephoneEtablissement;
	
	@NotNull
	private String emailEtablissement;
	
	@OneToMany(mappedBy = "etablissement",fetch = FetchType.LAZY)
	private Collection<Departement> departements;

	public Etablissement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Etablissement(@NotNull String nomEtablissement, @NotNull String adresseEtablissement,
			@NotNull String telephoneEtablissement, @NotNull String emailEtablissement) {
		super();
		this.nomEtablissement = nomEtablissement;
		this.adresseEtablissement = adresseEtablissement;
		this.telephoneEtablissement = telephoneEtablissement;
		this.emailEtablissement = emailEtablissement;
	}

	public int getIdEtablissement() {
		return idEtablissement;
	}

	public void setIdEtablissement(int idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

	public String getNomEtablissement() {
		return nomEtablissement;
	}

	public void setNomEtablissement(String nomEtablissement) {
		this.nomEtablissement = nomEtablissement;
	}

	public String getAdresseEtablissement() {
		return adresseEtablissement;
	}

	public void setAdresseEtablissement(String adresseEtablissement) {
		this.adresseEtablissement = adresseEtablissement;
	}

	public String getTelephoneEtablissement() {
		return telephoneEtablissement;
	}

	public void setTelephoneEtablissement(String telephoneEtablissement) {
		this.telephoneEtablissement = telephoneEtablissement;
	}

	public String getEmailEtablissement() {
		return emailEtablissement;
	}

	public void setEmailEtablissement(String emailEtablissement) {
		this.emailEtablissement = emailEtablissement;
	}

	public Collection<Departement> getDepartements() {
		return departements;
	}

	public void setDepartements(Collection<Departement> departements) {
		this.departements = departements;
	}
	
	
	
}
