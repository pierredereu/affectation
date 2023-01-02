package graphes;

import java.util.List;

public class Professeur {
	private List<Ressources> ressources;
	private String nom;
	private String prenom;
	
	public Professeur(String nom,String prenom, List<Ressources> ressources) {
		this.nom = nom;
		this.prenom = prenom;
		this.ressources = ressources;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public String getPrenom() {
		return this.prenom;
	}
	
	public List<Ressources> getRessources() {
		return ressources;
	}
}
