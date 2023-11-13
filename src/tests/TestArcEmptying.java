package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exceptions.NegativeNbTokensException;
import Exceptions.NegativeWeightException;
import mainPackage.ArcEmptying;
import mainPackage.ArcZero;
import mainPackage.Place;
import mainPackage.Transition;

public class TestArcEmptying {
	
	static Place p0;
	static Place p1;
	static Transition t0;
	static ArcEmptying a0;
	static ArcEmptying a1;
	
	@BeforeAll
	public static void setup() throws NegativeWeightException, NegativeNbTokensException {
		p0 = new Place(0, 3);
		p1 = new Place (1, 0);
		t0 = new Transition(0);
		a0 = new ArcEmptying(0, p0, t0);
		a1 = new ArcEmptying(1, p1, t0);
	}
	
	@Test
	public void testConstructor() throws NegativeWeightException {
		a0 = new ArcEmptying(0, p0, t0);
		Assertions.assertEquals(p0, a0.getPlace());
		Assertions.assertEquals(t0, a0.getTransition());
		Assertions.assertEquals(1, a0.getWeight());
		Assertions.assertTrue(a0 instanceof ArcEmptying);
	}
	
	@Test
	public void testStartExchange() throws Exception {
		a0.startExchange();
		Assertions.assertEquals(0, p0.getNbTokens());
		a1.startExchange();
		Assertions.assertEquals(0, p1.getNbTokens());
	}
	
	@Test
	public void testCheckAvailability() throws NegativeWeightException, NegativeNbTokensException {
		p0 = new Place(0, 3);
		p1 = new Place (1, 0);
		a0 = new ArcEmptying(0, p0,t0);
		a1 = new ArcEmptying(1, p1,t0);
		Assertions.assertTrue(a0.checkAvailability());
		Assertions.assertFalse(a1.checkAvailability());
	}
}
