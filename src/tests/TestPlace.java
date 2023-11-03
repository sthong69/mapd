package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import Exceptions.NegativeNbTokensException;
import mainPackage.ArcIn;
import mainPackage.Place;



public class TestPlace {
	static Place p0;
	
	@BeforeAll
	public static void setup() {
		p0 = new Place(0);
	}
	
	@Test  
	public void testConstructor() {
		p0 = new Place(0);
		Assertions.assertEquals(0,p0.getNbTokens());
		Assertions.assertEquals(0,p0.getArcList().size());
	}
	
	@Test
	public void testAddTokens() throws NegativeNbTokensException {
		p0 = new Place(0);
		// CAJ0
		NegativeNbTokensException exceptionMessage = Assertions.assertThrows(NegativeNbTokensException.class, ()->{p0.addTokens(-17);});
		Assertions.assertEquals("La quantité de jetons à ajouter ne peut pas être négative !",exceptionMessage.getMessage());
		
		// CAJ1
		p0.addTokens(17);
		Assertions.assertEquals(17, p0.getNbTokens());
	}
	
	@Test
	public void testRemoveTokens() throws NegativeNbTokensException {
		p0 = new Place(17);
		// CEJ0
		NegativeNbTokensException exceptionMessage0 = Assertions.assertThrows(NegativeNbTokensException.class, ()->{p0.removeTokens(-17);});
		Assertions.assertEquals("La quantité de jetons à enlever ne peut pas être négative !",exceptionMessage0.getMessage());
		
		// CEJ1
		NegativeNbTokensException exceptionMessage1 = Assertions.assertThrows(NegativeNbTokensException.class, ()->{p0.removeTokens(18);});
		Assertions.assertEquals("La quantité de jetons finale ne peut pas être négative !",exceptionMessage1.getMessage());
		
		// CEJ2
		p0.removeTokens(7);
		Assertions.assertEquals(10,p0.getNbTokens());
	}
	
	@Test
	public void testIsEmpty() throws NegativeNbTokensException {
		p0 = new Place(0);
		Assertions.assertTrue(p0.isEmpty());
		p0.addTokens(1);
		Assertions.assertFalse(p0.isEmpty());
	}
}
