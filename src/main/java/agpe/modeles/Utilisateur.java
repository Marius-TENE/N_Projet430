package agpe.modeles;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
public class Utilisateur {
	
	@Id
	@NotEmpty(message = "Entrer votre matricule svp")
	private String matricule;
	
	@Column(name = "nom")
    @NotEmpty(message = "*Entrer un nom svp")
	private String nom;
	
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
	
	@Column(name = "active")
	private int active;
	
	private String departement;
	private String grade;
	private String specialite;
	private String poste; // pour la sspe
	
	@OneToMany(mappedBy = "utilisateur",fetch = FetchType.LAZY)
	private Collection<Piece> pieces;
	
	@ManyToOne
	@JoinTable(name = "idRole")
	private Role role;
	
	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Utilisateur(@NotEmpty(message = "Entrer votre matricule svp") String matricule,
			@NotEmpty(message = "*Entrer un nom svp") String nom, String prenom,
			@Email(message = "*Entrer un email valide svp ") @NotEmpty(message = "*Entrer un email") String email,
			@NotEmpty(message = "*Entrer un login svp") String login,
			@Length(min = 5, message = "*Votre mot de passe doit avoir au moins 8 caractères") @NotEmpty(message = "*Entrer un mot de passe") String password,
			@NotEmpty(message = "*Entrer une adresse svp") String adresse,
			@NotEmpty(message = "*Entrer un numéro de téléphone svp") String tel,
			@NotEmpty(message = "Veuillez entre une date de naissance") String naissance, int active,
			String departement, String grade, String specialite, Role role) {
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
		this.active = active;
		this.departement = departement;
		this.grade = grade;
		this.specialite = specialite;
		this.role = role;
	}

	public Utilisateur(@NotEmpty(message = "Entrer votre matricule svp") String matricule,
			@NotEmpty(message = "*Entrer un nom svp") String nom, String prenom,
			@Email(message = "*Entrer un email valide svp ") @NotEmpty(message = "*Entrer un email") String email,
			@NotEmpty(message = "*Entrer un login svp") String login,
			@Length(min = 5, message = "*Votre mot de passe doit avoir au moins 8 caractères") @NotEmpty(message = "*Entrer un mot de passe") String password,
			@NotEmpty(message = "*Entrer un numéro de téléphone svp") String tel,
			@NotEmpty(message = "Veuillez entre une date de naissance") String naissance, int active,
			String departement, String grade, Role role) {
		super();
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.login = login;
		this.password = password;
		this.tel = tel;
		this.naissance = naissance;
		this.active = active;
		this.departement = departement;
		this.grade = grade;
		this.role = role;
	}

	public Utilisateur(@NotEmpty(message = "Entrer votre matricule svp") String matricule,
			@NotEmpty(message = "*Entrer un nom svp") String nom, String prenom,
			@Email(message = "*Entrer un email valide svp ") @NotEmpty(message = "*Entrer un email") String email,
			@NotEmpty(message = "*Entrer un login svp") String login,
			@Length(min = 5, message = "*Votre mot de passe doit avoir au moins 8 caractères") @NotEmpty(message = "*Entrer un mot de passe") String password,
			@NotEmpty(message = "*Entrer une adresse svp") String adresse,
			@NotEmpty(message = "*Entrer un numéro de téléphone svp") String tel,
			@NotEmpty(message = "Veuillez entre une date de naissance") String naissance, int active, String poste,
			Role role) {
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
		this.active = active;
		this.poste = poste;
		this.role = role;
	}

	public Utilisateur(@NotEmpty(message = "Entrer votre matricule svp") String matricule,
			@NotEmpty(message = "*Entrer un nom svp") String nom,
			@Email(message = "*Entrer un email valide svp ") @NotEmpty(message = "*Entrer un email") String email,
			@NotEmpty(message = "*Entrer un login svp") String login,
			@Length(min = 5, message = "*Votre mot de passe doit avoir au moins 8 caractères") @NotEmpty(message = "*Entrer un mot de passe") String password,
			@NotEmpty(message = "*Entrer un numéro de téléphone svp") String tel,
			@NotEmpty(message = "Veuillez entre une date de naissance") String naissance, int active, String poste,
			Role role) {
		super();
		this.matricule = matricule;
		this.nom = nom;
		this.email = email;
		this.login = login;
		this.password = password;
		this.tel = tel;
		this.naissance = naissance;
		this.active = active;
		this.poste = poste;
		this.role = role;
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

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
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

	public String getPoste() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}

	public Collection<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(Collection<Piece> pieces) {
		this.pieces = pieces;
	}

	public Role getRoles() {
		return role;
	}

	public void setRoles(Role roles) {
		this.role = roles;
	}

	

}