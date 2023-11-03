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
		NegativeNbTokensException exception = Assertions.assertThrows(NegativeNbTokensException.class, ()->{p0.addTokens(-17);});
		Assertions.assertEquals("WARNING: The number of tokens to add can not be negative.",exception.getMessage());
		
		// CAJ1
		p0.addTokens(17);
		Assertions.assertEquals(17, p0.getNbTokens());
	}
	
	@Test
	public void testRemoveTokens() throws NegativeNbTokensException {
		p0 = new Place(17);
		// CEJ0
		NegativeNbTokensException exception0 = Assertions.assertThrows(NegativeNbTokensException.class, ()->{p0.removeTokens(-17);});
		Assertions.assertEquals("WARNING: The number of tokens to remove can not be negative.",exception0.getMessage());
		
		// CEJ1
		NegativeNbTokensException exception1 = Assertions.assertThrows(NegativeNbTokensException.class, ()->{p0.removeTokens(18);});
		Assertions.assertEquals("WARNING: The final number of tokens can not be negative.",exception1.getMessage());
		
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
