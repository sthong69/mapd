package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Exceptions.NegativeNbTokensException;
import Exceptions.NegativeWeightException;
import mainPackage.ArcIn;
import mainPackage.Place;
import mainPackage.Transition;

public class TestArcIn {
	
	static Place p0;
	static Transition t0;
	static ArcIn a0;
	static ArcIn a1;
	static ArcIn a2;
	
	@BeforeAll
	public static void setup() throws NegativeWeightException{
		p0 = new Place(10);
		t0 = new Transition();
		a0 = new ArcIn(3, p0, t0); 
		a1 = new ArcIn(p0, t0);
	}
	
	@Test
	public void testConstructor() throws NegativeWeightException  {
		// CAE0
		a0 = new ArcIn(p0, t0);
		Assertions.assertEquals(1, a0.getWeight());
		Assertions.assertEquals(p0, a0.getPlace());
		Assertions.assertEquals(t0, a0.getTransition());
		System.out.println(t0.getArcInList().get(0));
		Assertions.assertTrue(t0.getArcInList().contains(a0));
		
		// CAE1
		a1 = new ArcIn(3, p0, t0);
		Assertions.assertEquals(3, a1.getWeight());
		Assertions.assertEquals(p0, a1.getPlace());
		Assertions.assertEquals(t0, a1.getTransition());
		Assertions.assertTrue(t0.getArcInList().contains(a1));
		
		// CAEe
		NegativeWeightException exception = Assertions.assertThrows(NegativeWeightException.class, ()->a2=new ArcIn(-2,p0,t0));
		Assertions.assertEquals("WARNING: An arc can not have a negative weight.", exception.getMessage());
	}
	
	@Test
	public void testStartExchange() throws NegativeNbTokensException, NegativeWeightException {
		a0.startExchange();
		Assertions.assertEquals(7, p0.getNbTokens());
		Place p1 = new Place(2);
		a2 = new ArcIn(5, p1, t0);
		NegativeNbTokensException exception = Assertions.assertThrows(NegativeNbTokensException.class, () -> a2.startExchange());
		Assertions.assertEquals("WARNING: The final number of tokens can not be negative.", exception.getMessage());
	}
	
	@Test
	public void testCheckAvailability() {
		Assertions.assertTrue(a0.checkAvailability());
		Assertions.assertFalse(a2.checkAvailability());
	}
}