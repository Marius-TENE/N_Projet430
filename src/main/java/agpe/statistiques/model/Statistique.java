package agpe.statistiques.model;

import agpe.modeles.Etablissement;

public class Statistique {
	
	private Etablissement etablissement;
	private int nombre;
	
	
	public Statistique() {
		super();
	}
	public Statistique(Etablissement etablissement, int nombre) {
		super();
		this.etablissement = etablissement;
		this.nombre = nombre;
	}
	public Etablissement getEtablissement() {
		return etablissement;
	}
	public void setEtablissement(Etablissement etablissement) {
		this.etablissement = etablissement;
	}
	public int getNombre() {
		return nombre;
	}
	public void setNombre(int nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Graphique [etablissement=" + etablissement + ", nombre=" + nombre + "]";
	}

	
}
