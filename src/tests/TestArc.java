package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Exceptions.NegativeNbTokensException;
import Exceptions.NegativeWeightException;
import mainPackage.Arc;
import mainPackage.ArcIn;
import mainPackage.Place;
import mainPackage.Transition;

public class TestArc {
	
	static Place p0;
	static Transition t0;
	static Arc a0;
	
	@BeforeAll
	public static void setup() throws NegativeWeightException, NegativeNbTokensException{
		p0 = new Place (0, 0);
		t0 = new Transition(0);
		a0 = new ArcIn(0, 5, p0, t0);
	}
	
	@Test
	public void testGetter() {
		Assertions.assertEquals("A_0", a0.getId());
		Assertions.assertEquals(5, a0.getWeight());
		Assertions.assertEquals(p0, a0.getPlace());
		Assertions.assertEquals(t0, a0.getTransition());
	}
	
	@Test
	public void testToString() {
		Assertions.assertEquals("A_0 : Weight = 5",a0.toString());
	}
	
	
}
