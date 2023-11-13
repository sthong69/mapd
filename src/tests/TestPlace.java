package tests;

import java.util.LinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Exceptions.NegativeNbTokensException;
import Exceptions.NegativeWeightException;
import mainPackage.Arc;
import mainPackage.ArcIn;
import mainPackage.ArcOut;
import mainPackage.Place;
import mainPackage.Transition;



public class TestPlace {
	static Place p0;
	
	@BeforeAll
	public static void setup() throws NegativeNbTokensException {
		p0 = new Place(0, 0);
	}
	
	@Test  
	public void testConstructor() throws NegativeNbTokensException {
		p0 = new Place(0, 0);
		Assertions.assertEquals("P_0", p0.getId());
		Assertions.assertEquals(0,p0.getNbTokens());
		Assertions.assertEquals(0,p0.getArcList().size());
		NegativeNbTokensException exception = Assertions.assertThrows(NegativeNbTokensException.class, ()-> {Place p1 = new Place(2, -2);});
		Assertions.assertEquals("Number of tokens cannot be negative.", exception.getMessage());
	}
	
	@Test
	public void testAddTokens() throws NegativeNbTokensException {
		p0 = new Place(0, 0);
		// CAJ0
		NegativeNbTokensException exception = Assertions.assertThrows(NegativeNbTokensException.class, ()->{p0.addTokens(-17);});
		Assertions.assertEquals("WARNING: The number of tokens to add can not be negative.",exception.getMessage());
		
		// CAJ1
		p0.addTokens(17);
		Assertions.assertEquals(17, p0.getNbTokens());
	}
	
	@Test
	public void testRemoveTokens() throws NegativeNbTokensException {
		p0 = new Place(0, 17);
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
		p0 = new Place(0, 0);
		Assertions.assertTrue(p0.isEmpty());
		p0.addTokens(1);
		Assertions.assertFalse(p0.isEmpty());
	}
	
	@Test
	public void testAddArc() throws NegativeWeightException, NegativeNbTokensException {
		Transition t0 = new Transition(0);
		Arc a3 = new ArcIn(3,2,p0,t0);
		Place p1 = new Place(1,0);
		p1.addArc(a3);
		Assertions.assertEquals(1, p1.getArcList().size());
	}
	
	@Test
	public void testRemoveArc() throws NegativeWeightException, NegativeNbTokensException {
		Transition t0 = new Transition(0);
		Arc a3 = new ArcIn(3,2,p0,t0);
		Place p1 = new Place(1,0);
		p1.addArc(a3);
		Assertions.assertEquals(1, p1.getArcList().size());
		p1.removeArc(a3);
		Assertions.assertEquals(0, p1.getArcList().size());
	}
	
	@Test
	public void testToString() throws NegativeNbTokensException {
		Place p2 = new Place(2, 3);
		Assertions.assertEquals("P_2 : tokens = 3", p2.toString());
	}
}
