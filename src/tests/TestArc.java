package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
	public static void setup() throws NegativeWeightException{
		p0 = new Place (0);
		t0 = new Transition();
		a0 = new ArcIn(5, p0, t0);
	}
		
	@Test
	public void testGetter() {
		Assertions.assertEquals(5, a0.getWeight());
		Assertions.assertEquals(p0, a0.getPlace());
		Assertions.assertEquals(t0, a0.getTransition());
		System.out.println(a0.toString());
		//Assertions.assertEquals("Weigh", a0.toString());

	}
	
	
	
}
