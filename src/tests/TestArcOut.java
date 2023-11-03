package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Exceptions.NegativeNbTokensException;
import Exceptions.NegativeWeightException;
import mainPackage.ArcOut;
import mainPackage.Place;
import mainPackage.Transition;

public class TestArcOut {
	
	static ArcOut a0;
	static ArcOut a1;
	static ArcOut a2;
	static Place p0;
	static Transition t0;
	
	@BeforeAll
	public static void setup() throws NegativeWeightException {
		p0 = new Place(2);
		t0 = new Transition();
		a0 = new ArcOut(p0, t0);
		a1 = new ArcOut(3, p0, t0); 
	}
	
	@Test
	public void testConstructor() throws NegativeWeightException {
		// CAS0
		a0 = new ArcOut(p0, t0);
		Assertions.assertEquals(1, a0.getWeight());
		Assertions.assertEquals(p0, a0.getPlace());
		Assertions.assertEquals(t0, a0.getTransition());
		Assertions.assertTrue(t0.getArcOutList().contains(a0));
		
		
		// CAS1
		a1 = new ArcOut(3, p0, t0);
		Assertions.assertEquals(3, a1.getWeight());
		Assertions.assertEquals(p0, a1.getPlace());
		Assertions.assertEquals(t0, a1.getTransition());
		Assertions.assertTrue(t0.getArcOutList().contains(a1));
		
		// CASe
		NegativeWeightException exception = Assertions.assertThrows(NegativeWeightException.class, ()-> a2 = new ArcOut(-2,p0,t0));
		Assertions.assertEquals("WARNING: An arc can not have a negative weight.", exception.getMessage());
	}
	
	@Test
	public void testStartExchange() throws NegativeNbTokensException {
		a1.startExchange();
		Assertions.assertEquals(5, p0.getNbTokens());
	}

}
