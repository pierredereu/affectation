package graphes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;

public class Affectation {
	private List<Tuteur> tuteurs;
	private List<Tutore> tutores;
	private GrapheNonOrienteValue<Etudiant> association;
	private final int POIDSFORCAGE;

	public List<Tuteur> getTuteurs() { return this.tuteurs; }
	public List<Tutore> getTutores() { return this.tutores; }
	public GrapheNonOrienteValue<Etudiant> getAssociation() { return this.association; }
	
	/**
	 * Calcul le nombre de tutorés pouvant être assignés à chaque tuteurs.
	 */
	private void maxTutores() {
		List<Tuteur> ejectes = new ArrayList<Tuteur>();
		List<Tuteur> nonEjectes = new ArrayList<Tuteur>();
		for (Tuteur tmpt : tuteurs) {
			if (tmpt.estEjecte()) {
				ejectes.add(tmpt);
				tmpt.setMaxTutores(1);
			} else {
				nonEjectes.add(tmpt);
			}
		}
		
		Collections.sort(nonEjectes);
		Collections.reverse(nonEjectes);
		int index = 0;
		for (Tuteur tmpt : nonEjectes) {
			int res = (int) Math.floor((double) ((tutores.size() - ejectes.size())) / nonEjectes.size());
			int reste = (tutores.size() - ejectes.size()) % nonEjectes.size();
			if (index < reste) res +=1;
			tmpt.setMaxTutores(res);
			index++;
		}
	}
	
	/**
	 * Génère les IDs des Etudiants.
	 */
	private void genererIDs() {
		for (int i=0;i<tuteurs.size();i++) {
			Tuteur t = tuteurs.get(i);
			t.setID(t.getPrenom().toLowerCase() + "." + t.getNom().toLowerCase() + ".tut");
		}
		for (int i=0;i<tutores.size();i++) {
			Tutore t = tutores.get(i);
			t.setID(t.getPrenom().toLowerCase() + "." + t.getNom().toLowerCase() + ".ele");
		}
	}
	
	public Affectation(List<Tuteur> tutorants, List<Tutore> tutores) {
		this.tuteurs = tutorants;
		this.tutores = tutores;
		maxTutores();
		genererIDs();
		this.POIDSFORCAGE = -100;
		association = new GrapheNonOrienteValue<Etudiant>();
	}
	
	/**
	 * Duplique les tuteurs pour être en accord avec le nombre de tutorés.
	 * 
	 * @return List
	 */
	private List<Tuteur> dupliquerTutorants() {
		List<Tuteur> newTutorants = new ArrayList<Tuteur>();
		for (int i=0;i<this.tuteurs.size();i++) {
			for (int j=0;j<this.tuteurs.get(i).getMaxTutores();j++) {
				Tuteur t;
				if (j==0) {
					t = tuteurs.get(i);
				} else {
					t = new Tuteur(tuteurs.get(i));					
				}
				newTutorants.add(t);
			}
		}
		return newTutorants;
	}
	
	/**
	 * Crée des tutorés fictifs afin d'être en accord avec le nombre de tuteurs.
	 * 
	 * @return List
	 */
	private List<Tutore> creerTutoresFictifs() {
		Tutore tutoreFictif = new Tutore("Fictif", "Ejection", 1, 20.0,null, 0, "fictif.ejection.ele");
		List<Tutore> newTutores = new ArrayList<Tutore>();
		for (int i=0;i<this.tutores.size();i++) {
			newTutores.add(tutores.get(i));
		}
		for (int i=0;i<this.tuteurs.size()-this.tutores.size();i++) {
			newTutores.add(new Tutore(tutoreFictif));
		}
		return newTutores;
	}

	/**
	 * Force l'ajout d'un couple Tuteur/Tutoré dans l'association.<br>
	 * Ne fera rien si un des étudiants existe déjà dans celle-ci.
	 * 
	 * @param Tuteur, Tutore
	 */
	public void forcerCouple(Tuteur tuteur, Tutore tutore) {
		if (!association.contientSommet(tuteur) && !association.contientSommet(tutore)) {
			List<Tuteur> tmp = new ArrayList<Tuteur>();
			for (int i=0;i<tuteurs.size();i++) {
				if (tuteurs.get(i).toString().equals(tuteur.toString())) {
					tmp.add(tuteurs.get(i));
				}
			}
			boolean added = false;
			int i=0;
			while (i<tmp.size() && !added) {
				if (!association.contientSommet(tmp.get(i))) {
					association.ajouterSommet(tmp.get(i));
					added = true;
				}
				i++;
			}
			if (!association.contientSommet(tutore)) association.ajouterSommet(tutore);
			if (!association.contientArete(tuteur, tutore)) {
				association.ajouterArete(tuteur, tutore, POIDSFORCAGE);
			}
		}
	}
	
	/**
	 * Enlève de l'affectation un tuteur.
	 * 
	 * @param t
	 */
	public void ejecterEtudiant(Tuteur t) {
		t.ejecter();
		Tutore tutoreFictif = new Tutore("Fictif", "Ejection", 1, 20.0,null, 0, "fictif.ejection.ele");
		if (!association.contientSommet(t)) {
			tutores.add(tutoreFictif);
			association.ajouterSommet(t);
			association.ajouterSommet(tutoreFictif);
			association.ajouterArete(t, tutoreFictif, POIDSFORCAGE);
		}
	}

	/**
	 * Enlève de l'affectation un tutoré.
	 * 
	 * @param t
	 */
	public void ejecterEtudiant(Tutore t) {
		Tuteur tuteurFictif = new Tuteur("Fictif", "Ejection", 1, 0.0,null, 0, "fictif.ejection.tut");
		tuteurFictif.ejecter();
		if (!association.contientSommet(t)) {
			tuteurs.add(tuteurFictif);
			association.ajouterSommet(t);
			association.ajouterSommet(tuteurFictif);
			association.ajouterArete(tuteurFictif, t, POIDSFORCAGE);
		}
	}
	
	/**
	 * Génère le calcul d'affectation.
	 * 
	 * @return CalculAffectation
	 */
	public CalculAffectation<Etudiant> genererGroupes() {
		maxTutores();
		if (tuteurs.size() < tutores.size()) this.tuteurs = dupliquerTutorants();
		else if (tutores.size() < tuteurs.size()) this.tutores = creerTutoresFictifs();
		double coef = 1;
		Tuteur aTuteur;
		Tutore aTutore;
		for (int i=0;i<this.tutores.size();i++) {
			aTutore = this.tutores.get(i);
			if (!association.contientSommet(aTutore)) association.ajouterSommet(aTutore);
			for (int j=0;j<this.tuteurs.size();j++) {
				aTuteur = this.tuteurs.get(j);
				if (!association.contientSommet(aTuteur)) association.ajouterSommet(aTuteur);
				coef += i/10.0 + j/10.0;
				if (!association.contientArete(aTuteur, aTutore)) 
					association.ajouterArete(aTuteur, aTutore, (aTuteur.calculCout() + aTutore.calculCout())*coef);
				coef = 1;
			}
		}
		List<Etudiant> partieTuteurs = new ArrayList<Etudiant>();
		for (Tuteur t : this.tuteurs) {
			partieTuteurs.add(t);
		}
		List<Etudiant> partieTutores = new ArrayList<Etudiant>();
		for (Tutore t : this.tutores) {
			partieTutores.add(t);
		}
		
		CalculAffectation<Etudiant> calcul = new CalculAffectation<>(association, partieTuteurs, partieTutores);
		return calcul;
	}
}
