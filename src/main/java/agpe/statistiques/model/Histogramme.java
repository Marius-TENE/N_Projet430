package agpe.statistiques.model;

public class Histogramme {
	private String titre;
	private String dataPoints;
	private String type;
	public Histogramme() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Histogramme(String titre, String dataPoints, String type) {
		super();
		this.titre = titre;
		this.dataPoints = dataPoints;
		this.type = type;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDataPoints() {
		return dataPoints;
	}
	public void setDataPoints(String dataPoints) {
		this.dataPoints = dataPoints;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Histogramme [titre=" + titre + ", dataPoints=" + dataPoints + ", type=" + type + "]";
	}
	
	
}
