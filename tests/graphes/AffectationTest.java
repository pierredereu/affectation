package graphes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.ulille.but.sae2_02.graphes.Arete;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;

class AffectationTest {
	Tutore e1, e2, e3, e4, e9, e10;
	Tuteur e5, e6, e7, e8;
	List<Tutore> listeTutores;
	List<Tuteur> listeTuteurs;
	Affectation a;
	CalculAffectation<Etudiant> calcul;

	@BeforeEach
	void setUp() {
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
//		System.out.println("Affectation     = " + calcul.getAffectation().toString());
//		System.out.println("Cout            = " + calcul.getCout());
	}

	@Test
	void forcerCoupleTest() {
		boolean found = false;
		for (int i=0;i<calcul.getAffectation().size();i++) {
			if (calcul.getAffectation().get(i).getExtremite1().toString().equals(e5.toString()) &&
					calcul.getAffectation().get(i).getExtremite2().toString().equals(e1.toString())) {
				found = true;
			}
		}
		assertTrue(found);
	}

	@Test
	void ejecterEtudiantTest() {
		boolean found = false;
		for (int i=0;i<calcul.getAffectation().size();i++) {
			if (calcul.getAffectation().get(i).getExtremite1().toString().equals(e6.toString()) &&
					calcul.getAffectation().get(i).getExtremite2().toString().equals("fictif.ejection.ele")) {
				found = true;
			}
		}
		assertTrue(found);
		found = false;
		for (int i=0;i<calcul.getAffectation().size();i++) {
			if (calcul.getAffectation().get(i).getExtremite1().toString().equals("fictif.ejection.tut") &&
					calcul.getAffectation().get(i).getExtremite2().toString().equals(e4.toString())) {
				found = true;
			}
		}
		assertTrue(found);
	}

	@Test
	void calculTest() {
		List<Arete<Etudiant>> affect =calcul.getAffectation();
		Set<String> res = new HashSet<>();
		Tutore tutoreFictif = new Tutore("Fictif", "Ejection", 1, 20.0,null, 0, "fictif.ejection.ele");
		Tuteur tuteurFictif = new Tuteur("Fictif", "Ejection", 1, 0.0,null, 0, "fictif.ejection.tut");
		res.add((new Arete<Etudiant>(e5, e1)).toString());
		res.add((new Arete<Etudiant>(e6, tutoreFictif)).toString());
		res.add((new Arete<Etudiant>(e8, e9)).toString());
		res.add((new Arete<Etudiant>(tuteurFictif, e4)).toString());
		res.add((new Arete<Etudiant>(e7, e2)).toString());
		res.add((new Arete<Etudiant>(e7, e3)).toString());
		res.add((new Arete<Etudiant>(e5, e10)).toString());
		for(int i=0;i<affect.size();i=i+1) {
			assertTrue(res.contains(affect.get(i).toString()));
		}
	}
	
	@Test
	void maxTutoresTest() {
		assertEquals(2,e5.getMaxTutores());
		assertEquals(1,e6.getMaxTutores());
		assertEquals(2,e7.getMaxTutores());
		assertEquals(1,e8.getMaxTutores());
	}
	@Test
	void idTest() {
		assertEquals("albert.newton.tut",e5.getID());
		assertEquals("bastien.leleu.ele",e1.getID());
		assertEquals("pierre.dereu.ele",e3.getID());
		assertEquals("arnold.finito.tut",e8.getID());
	}
}

