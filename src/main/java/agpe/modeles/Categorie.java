package agpe.modeles;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import agpe.portfolio.modele.Piece;

@Entity
public class Categorie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCategorie;
	
	@NotNull
	private String nomCategorie;
	
	@OneToMany(mappedBy = "categorie",fetch = FetchType.LAZY)
	private Collection<Piece> pieces;
	
	public Categorie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Categorie(@NotNull String nomCategorie) {
		super();
		this.nomCategorie = nomCategorie;
	}
	public int getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}
	public String getNomCategorie() {
		return nomCategorie;
	}
	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}
	public Collection<Piece> getPieces() {
		return pieces;
	}
	public void setPieces(Collection<Piece> pieces) {
		this.pieces = pieces;
	}
	
	
}
