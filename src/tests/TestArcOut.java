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
	static Place p0;
	static Transition t0;
	
	@BeforeAll
	public static void setup() throws NegativeWeightException {
		p0 = new Place(2);
		t0 = new Transition();
		a0 = new ArcOut(3, p0, t0);
	}
	
	@Test
	public void testConstructor() throws NegativeWeightException {
		//TODO tester updateTransition
		a0 = new ArcOut(3, p0, t0);
		Assertions.assertEquals(3, a0.getWeight());
		Assertions.assertEquals(p0, a0.getPlace());
		Assertions.assertEquals(t0, a0.getTransition());
		Assertions.assertThrows(NegativeWeightException.class, ()-> a1 = new ArcOut(-2,p0,t0));
	}
	
	@Test
	public void testStartExchange() throws NegativeNbTokensException {
		a0.startExchange();
		Assertions.assertEquals(5, p0.getNbTokens());
	}

}
