package agpe.portfolio.modele;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import agpe.modeles.Categorie;
import agpe.modeles.Utilisateur;

@Entity
public class Piece {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPiece;
	
	@NotNull
	private Date date_ajout;
	
	@NotNull
	private String nomPiece;
	
	@NotNull
	private String extensionPiece;
	
	@Lob
	private byte[] data;
	
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

	public Piece(Long idPiece, @NotNull Date date_ajout, @NotNull String nomPiece, @NotNull String extensionPiece,
			byte[] data, Categorie categorie, Utilisateur utilisateur) {
		super();
		this.idPiece = idPiece;
		this.date_ajout = date_ajout;
		this.nomPiece = nomPiece;
		this.extensionPiece = extensionPiece;
		this.data = data;
		this.categorie = categorie;
		this.utilisateur = utilisateur;
	}

	public Piece(@NotNull Date date_ajout, @NotNull String nomPiece, @NotNull String extensionPiece, byte[] data,
			Categorie categorie, Utilisateur utilisateur) {
		super();
		this.date_ajout = date_ajout;
		this.nomPiece = nomPiece;
		this.extensionPiece = extensionPiece;
		this.data = data;
		this.categorie = categorie;
		this.utilisateur = utilisateur;
	}

	public Piece(@NotNull Date date_ajout, @NotNull String nomPiece, @NotNull String extensionPiece, byte[] data) {
		super();
		this.date_ajout = date_ajout;
		this.nomPiece = nomPiece;
		this.extensionPiece = extensionPiece;
		this.data = data;
	}

	public Long getIdPiece() {
		return idPiece;
	}

	public void setIdPiece(Long idPiece) {
		this.idPiece = idPiece;
	}

	public Date getDate_ajout() {
		return date_ajout;
	}

	public void setDate_ajout(Date date_ajout) {
		this.date_ajout = date_ajout;
	}

	public String getNomPiece() {
		return nomPiece;
	}

	public void setNomPiece(String nomPiece) {
		this.nomPiece = nomPiece;
	}

	public String getExtensionPiece() {
		return extensionPiece;
	}

	public void setExtensionPiece(String extensionPiece) {
		this.extensionPiece = extensionPiece;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
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
