package agpe.portfolio.modele;

public class NbrePieces {
	
	private int idCategorie;
	private int nbrePieces;
	public NbrePieces() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NbrePieces(int idCategorie, int nbrePieces) {
		super();
		this.idCategorie = idCategorie;
		this.nbrePieces = nbrePieces;
	}
	public int getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}
	public int getNbrePieces() {
		return nbrePieces;
	}
	public void setNbrePieces(int nbrePieces) {
		this.nbrePieces = nbrePieces;
	}
	
}
