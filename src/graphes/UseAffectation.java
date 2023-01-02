package graphes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.ulille.but.sae2_02.graphes.CalculAffectation;

public class UseAffectation {
	public static void main(String[] args) {
		Tutore e1, e2, e3, e4, e9, e10;
		Tuteur e5, e6, e7, e8;
		List<Tutore> listeTutores;
		List<Tuteur> listeTuteurs;
		Affectation a;
		CalculAffectation<Etudiant> calcul;
		e1 = new Tutore("Leleu", "Bastien", 1, 12.2,Ressources.R101, 1);
		e2 = new Tutore("François", "Antonin", 1, 15.45,Ressources.R101, 2);
		e3 = new Tutore("Dereu", "Pierre", 1, 14.8,Ressources.R101, 1);
		e4 = new Tutore("Chassé", "Fabien", 1, 9.12,Ressources.R101, 7);
		e9 = new Tutore("Lecrack", "Philippe", 1, 18.7,Ressources.R101, 0);
		e10 = new Tutore("Mirpon", "Cubrik", 1, 6.0,Ressources.R101, 13);
	    listeTutores = new ArrayList<Tutore>();
	    listeTutores.add(e1);
	    listeTutores.add(e2);
	    listeTutores.add(e3);
	    listeTutores.add(e4);
	    listeTutores.add(e9);
	    listeTutores.add(e10);
	    Collections.sort(listeTutores);
	    e5 = new Tuteur("Newton", "Albert", 3, 19.61,Ressources.R101, 1);
	    e6 = new Tuteur("Michel", "Jean", 3, 9.33,Ressources.R101, 4);
	    e7 = new Tuteur("Lagrange", "Sacha", 2, 13.57,Ressources.R101, 0);
	    e8 = new Tuteur("Finito", "Arnold", 2, 10.11,Ressources.R101, 0);
	    listeTuteurs = new ArrayList<Tuteur>();
	    listeTuteurs.add(e5);
	    listeTuteurs.add(e6);
	    listeTuteurs.add(e7);
	    listeTuteurs.add(e8);
	    Collections.sort(listeTuteurs);
	    
		a = new Affectation(listeTuteurs, listeTutores);
		
		a.forcerCouple(e5, e1);
		a.forcerCouple(e5, e3);
		a.forcerCouple(e6, e1);

		a.ejecterEtudiant(e6);
		a.ejecterEtudiant(e4);
		
		calcul = a.genererGroupes();
		System.out.println("Affectation     = " + calcul.getAffectation().toString());
		System.out.println("Cout            = " + calcul.getCout());
	}
	
}
