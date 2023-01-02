package graphes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculCoutTest {
	Tutore e1, e2;
	Tuteur  e6, e7;
	
	@BeforeEach
	void setUp() {
		e1 = new Tutore("Leleu", "Bastien", 1, 12.2,Ressources.R101, 1);
		e2 = new Tutore("François", "Antonin", 1, 15.45,Ressources.R101, 2);
	    e6 = new Tuteur("Michel", "Jean", 3, 9.33,Ressources.R101, 4);
	    e7 = new Tuteur("Lagrange", "Sacha", 2, 13.57,Ressources.R101, 0);

	}
	@Test
	void calculCoutTest() {
		assertEquals(12.7, e1.calculCout());
		assertEquals(16.45, e2.calculCout());
		assertEquals(9.67, e6.calculCout());
		assertEquals(6.43, e7.calculCout());
		
	}
}
