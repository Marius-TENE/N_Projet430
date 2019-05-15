package agpe.modeles;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import agpe.modeles.Utilisateur;

@Entity
public class Role {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRole;
	
	@NotNull
	private String nomRole;

	@OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
	private Collection<Utilisateur> utilisateurs;
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Role(@NotNull String nomRole) {
		super();
		this.nomRole = nomRole;
	}
	public int getIdRole() {
		return idRole;
	}
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}
	public String getNomRole() {
		return nomRole;
	}
	public void setNomRole(String nomRole) {
		this.nomRole = nomRole;
	}
	public Collection<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}
	public void setUtilisateurs(Collection<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	
}
