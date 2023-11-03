package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Exceptions.NegativeWeightException;
import mainPackage.ArcEmptying;
import mainPackage.ArcIn;
import mainPackage.ArcOut;
import mainPackage.ArcZero;
import mainPackage.Place;
import mainPackage.Transition;

public class TestTransition {
	static Transition t0;
	static Transition t1;
	static Transition t2;
	static Transition t3;
	
	
	@BeforeAll
	public static void setup() {
		t0 = new Transition();
	}
	
	@Test
	public void testConstructor() {
		// RI
		t0 = new Transition();
		Assertions.assertEquals(0, t0.getArcInList().size());
		Assertions.assertEquals(0, t0.getArcOutList().size());
	}
	
	@Test
	public void testRemoveArcIn() throws NegativeWeightException {
		ArcIn a0 = new ArcIn(null, t0);
		ArcIn a1 = new ArcIn(null, t0);
		t0.removeArcIn(a1);
		Assertions.assertTrue(t0.getArcInList().contains(a0));
		Assertions.assertFalse(t0.getArcInList().contains(a1));
	}
	
	@Test
	public void testRemoveArcOut() throws NegativeWeightException {
		ArcOut a0 = new ArcOut(null, t0);
		ArcOut a1 = new ArcOut(null, t0);
		t0.removeArcOut(a1);
		Assertions.assertTrue(t0.getArcOutList().contains(a0));
		Assertions.assertFalse(t0.getArcOutList().contains(a1));
	}
	
	@Test
	public void testIsDrawable() throws NegativeWeightException {
		t0 = new Transition();
		t1 = new Transition();
		t2 = new Transition();
		t3 = new Transition();
		
		Place p01 = new Place(1);
		Place p11 = new Place(0);
		ArcIn a01 = new ArcIn(p01,t0);
		ArcIn a11 = new ArcIn(p11,t1);
		ArcIn a21 = new ArcIn(p01,t2);
		ArcIn a31 = new ArcIn(p01,t3);
		
		Place p02 = new Place(0);
		Place p12 = new Place(1);
		ArcZero a02 = new ArcZero(p02,t0);
		ArcZero a12 = new ArcZero(p02,t1);
		ArcZero a22 = new ArcZero(p12,t2);
		ArcZero a32 = new ArcZero(p02,t3);
		
		Place p03 = new Place(100);
		Place p13 = new Place(0);
		ArcEmptying a03 = new ArcEmptying(p03,t0);
		ArcEmptying a13 = new ArcEmptying(p03,t1);
		ArcEmptying a23 = new ArcEmptying(p03,t2);
		ArcEmptying a33 = new ArcEmptying(p13,t3);
		
		Assertions.assertTrue(t0.isDrawable());
		Assertions.assertFalse(t1.isDrawable());
		Assertions.assertFalse(t2.isDrawable());
		Assertions.assertFalse(t3.isDrawable());
	}
}
