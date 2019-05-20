package agpe.portfolio.modele;

public class CreerPortfolioDao {
	
	private String matricule;
	private String nom;
	private String prenom;
	private String email;
	private String adresse;
	private String tel;
	private String naissance;
	private String grade;
	private String specialite;
	private String sexe;
	private int idDepartement;
	public CreerPortfolioDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CreerPortfolioDao(String matricule, String nom, String prenom, String email, String adresse, String tel,
			String naissance, String grade, String specialite, String sexe, int idDepartement) {
		super();
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
		this.tel = tel;
		this.naissance = naissance;
		this.grade = grade;
		this.specialite = specialite;
		this.sexe = sexe;
		this.idDepartement = idDepartement;
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
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public int getIdDepartement() {
		return idDepartement;
	}
	public void setIdDepartement(int idDepartement) {
		this.idDepartement = idDepartement;
	}
	@Override
	public String toString() {
		return "CreerPortfolioDao [matricule=" + matricule + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email
				+ ", adresse=" + adresse + ", tel=" + tel + ", naissance=" + naissance + ", grade=" + grade
				+ ", specialite=" + specialite + ", sexe=" + sexe + ", idDepartement=" + idDepartement + "]";
	}
	
	
}
