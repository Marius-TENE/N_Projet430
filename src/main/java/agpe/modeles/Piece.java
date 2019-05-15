package agpe.modeles;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Piece {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPiece;
	
	@NotNull
	private String nomPiece;
	
	@NotNull
	private Date date_ajout;
	
	@NotNull
	private String adresse;
	
	@ManyToOne
	@JoinColumn(name = "idCategorie")
	private Categorie categorie;
	
	@ManyToOne
	@JoinColumn(name = "matricule")
	private Utilisateur utilisateur;
	
	public Piece() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Piece(@NotNull String nomPiece, @NotNull Date date_ajout, @NotNull String adresse, Categorie categorie,
			Utilisateur utilisateur) {
		super();
		this.nomPiece = nomPiece;
		this.date_ajout = date_ajout;
		this.adresse = adresse;
		this.categorie = categorie;
		this.utilisateur = utilisateur;
	}

	public Long getIdPiece() {
		return idPiece;
	}

	public void setIdPiece(Long idPiece) {
		this.idPiece = idPiece;
	}

	public String getNomPiece() {
		return nomPiece;
	}

	public void setNomPiece(String nomPiece) {
		this.nomPiece = nomPiece;
	}

	public Date getDate_ajout() {
		return date_ajout;
	}

	public void setDate_ajout(Date date_ajout) {
		this.date_ajout = date_ajout;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	
}
