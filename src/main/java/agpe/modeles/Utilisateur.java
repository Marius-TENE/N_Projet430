package agpe.modeles;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import agpe.portfolio.modele.Piece;

@Entity
public class Utilisateur {
	
	@Id
	@NotEmpty(message = "Entrer un matricule svp")
	private String matricule;
	
	@Column(name = "nom")
    @NotEmpty(message = "*Entrer un nom svp")
	private String nom;
	
	@NotNull
	private String sexe;
	
	@Column(nullable = true)
	private String prenom;
	
	@Column(name = "email")
    @Email(message = "*Entrer un email valide svp ")
    @NotEmpty(message = "*Entrer un email")
	private String email;
	
	@Column(nullable = true,unique = true)	
	@NotEmpty(message = "*Entrer un login svp")
	private String login;
	
	@Column(name = "password")
    @Length(min = 5, message = "*Votre mot de passe doit avoir au moins 8 caractères")
    @NotEmpty(message = "*Entrer un mot de passe")
	private String password;
	
	@NotEmpty(message = "*Entrer une adresse svp")
	private String adresse;
	
	@NotEmpty(message = "*Entrer un numéro de téléphone svp")
	private String tel;
	
	@NotEmpty(message = "Veuillez entre une date de naissance")
	private String naissance;
	
	@Column(name = "actif")
	private int actif;
	
	private String grade;
	private String specialite;

	@OneToMany(mappedBy = "utilisateur",fetch = FetchType.LAZY)
	private Collection<Piece> pieces;
	
	@ManyToOne
	@JoinColumn(name = "idDepartement")
	private Departement departement;
	@NotNull
	private String role;
	
	
	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	public Utilisateur(@NotEmpty(message = "Entrer un matricule svp") String matricule,
			@NotEmpty(message = "*Entrer un nom svp") String nom, String prenom,
			@Email(message = "*Entrer un email valide svp ") @NotEmpty(message = "*Entrer un email") String email,
			@NotEmpty(message = "*Entrer un login svp") String login,
			@Length(min = 5, message = "*Votre mot de passe doit avoir au moins 8 caractères") @NotEmpty(message = "*Entrer un mot de passe") String password,
			@NotEmpty(message = "*Entrer une adresse svp") String adresse,
			@NotEmpty(message = "*Entrer un numéro de téléphone svp") String tel,
			@NotEmpty(message = "Veuillez entre une date de naissance") String naissance, int actif, String grade,
			String specialite, Collection<Piece> pieces, Departement departement, String role,String sexe ) {
		super();
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.login = login;
		this.password = password;
		this.adresse = adresse;
		this.tel = tel;
		this.naissance = naissance;
		this.actif = actif;
		this.grade = grade;
		this.specialite = specialite;
		this.pieces = pieces;
		this.departement = departement;
		this.role = role;
		this.sexe=sexe;
	}


	public Utilisateur(@NotEmpty(message = "Entrer un matricule svp") String matricule,
			@NotEmpty(message = "*Entrer un nom svp") String nom,
			@Email(message = "*Entrer un email valide svp ") @NotEmpty(message = "*Entrer un email") String email,
			@NotEmpty(message = "*Entrer un login svp") String login,
			@Length(min = 5, message = "*Votre mot de passe doit avoir au moins 8 caractères") @NotEmpty(message = "*Entrer un mot de passe") String password,
			@NotEmpty(message = "*Entrer une adresse svp") String adresse,
			@NotEmpty(message = "*Entrer un numéro de téléphone svp") String tel,
			@NotEmpty(message = "Veuillez entre une date de naissance") String naissance, int actif, String grade,
			String specialite, Departement departement, String role,String sexe) {
		super();
		this.matricule = matricule;
		this.nom = nom;
		this.email = email;
		this.login = login;
		this.password = password;
		this.adresse = adresse;
		this.tel = tel;
		this.naissance = naissance;
		this.actif = actif;
		this.grade = grade;
		this.specialite = specialite;
		this.departement = departement;
		this.role = role;
		this.sexe=sexe;
	}




	public String getMatricule() {
		return matricule;
	}


	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	
	
	public String getSexe() {
		return sexe;
	}


	public void setSexe(String sexe) {
		this.sexe = sexe;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getNaissance() {
		return naissance;
	}


	public void setNaissance(String naissance) {
		this.naissance = naissance;
	}


	public int getActif() {
		return actif;
	}


	public void setActif(int actif) {
		this.actif = actif;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public String getSpecialite() {
		return specialite;
	}


	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}


	public Collection<Piece> getPieces() {
		return pieces;
	}


	public void setPieces(Collection<Piece> pieces) {
		this.pieces = pieces;
	}


	public Departement getDepartement() {
		return departement;
	}


	public void setDepartement(Departement departement) {
		this.departement = departement;
	}


	@Override
	public String toString() {
		return "Utilisateur [matricule=" + matricule + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email
				+ ", login=" + login + ", password=" + password + ", adresse=" + adresse + ", tel=" + tel
				+ ", naissance=" + naissance + ", actif=" + actif + ", grade=" + grade + ", specialite=" + specialite
				+ ", role=" + role + "]";
	}
	
	
	
}