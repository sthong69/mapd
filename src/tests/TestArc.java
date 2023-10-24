package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import MainPackage.Arc;
import MainPackage.Place;
import MainPackage.Transition;

public class TestArc {
	
	static Place p0;
	static Transition t0;
	static Arc a0;
	static Arc a1;
	
	@BeforeAll
	public static void setup() {
		p0 = new Place (0);
		t0 = new Transition();
		a0 = new Arc(5, p0, t0);
		a1 = new Arc(p0, t0);
	}
	
	@Test
	public void testGetPlace() {
		Assertions.assertEquals(p0, a0.getPlace());
		Assertions.assertEquals(p0, a1.getPlace());
	}

}
