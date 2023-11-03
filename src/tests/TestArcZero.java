package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Exceptions.NegativeWeightException;
import mainPackage.ArcZero;
import mainPackage.Place;
import mainPackage.Transition;

public class TestArcZero {
	
	static Place p0;
	static Place p1;
	static Transition t0;
	static ArcZero a0;
	static ArcZero a1;
	
	@BeforeAll
	public static void setup() throws NegativeWeightException {
		p0 = new Place(0);
		p1 = new Place(2);
		t0 = new Transition();
		a0 = new ArcZero(p0, t0);
		a1 = new ArcZero(p1, t0);		
	}
	
	@Test
	public void testConstructor() throws NegativeWeightException {
		a0 = new ArcZero(p0, t0);
		Assertions.assertEquals(p0, a0.getPlace());
		Assertions.assertEquals(t0, a0.getTransition());
		Assertions.assertEquals(1, a0.getWeight());

	}
	
	@Test
	public void testStartExchange() {
		a0.startExchange();
		Assertions.assertEquals(0, p0.getNbTokens());
	}
	
	@Test
	public void testCheckAvailability() {
		Assertions.assertTrue(a0.checkAvailability());
		Assertions.assertFalse(a1.checkAvailability());
	}
	
}