package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Exceptions.NegativeNbTokensException;
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
	static Place p0;
	
	
	@BeforeAll
	public static void setup() throws NegativeNbTokensException {
		t0 = new Transition(0);
		p0 = new Place(0, 0);
	}
	
	@Test
	public void testConstructor() {
		// RI
		t0 = new Transition(0);
		Assertions.assertEquals("T_0", t0.getId());
		Assertions.assertEquals(0, t0.getArcInList().size());
		Assertions.assertEquals(0, t0.getArcOutList().size());
	}
	
	@Test
	public void testToString() {
		Assertions.assertEquals("T_0", t0.toString());
	}
	
	@Test
	public void testRemoveArcIn() throws NegativeWeightException {
		ArcIn a0 = new ArcIn(0, p0, t0);
		ArcIn a1 = new ArcIn(1, p0, t0);
		t0.removeArcIn(a1);
		Assertions.assertTrue(t0.getArcInList().contains(a0));
		Assertions.assertFalse(t0.getArcInList().contains(a1));
	}
	
	@Test
	public void testRemoveArcOut() throws NegativeWeightException {
		ArcOut a0 = new ArcOut(0, p0, t0);
		ArcOut a1 = new ArcOut(1, p0, t0);
		t0.removeArcOut(a1);
		Assertions.assertTrue(t0.getArcOutList().contains(a0));
		Assertions.assertFalse(t0.getArcOutList().contains(a1));
	}
	
	@Test
	public void testIsDrawable() throws NegativeWeightException, NegativeNbTokensException {
		t0 = new Transition(0);
		t1 = new Transition(1);
		t2 = new Transition(2);
		t3 = new Transition(3);
		
		Place p01 = new Place(01, 1);
		Place p11 = new Place(11, 0);
		ArcIn a01 = new ArcIn(01, p01,t0);
		ArcIn a11 = new ArcIn(11, p11,t1);
		ArcIn a21 = new ArcIn(21, p01,t2);
		ArcIn a31 = new ArcIn(31, p01,t3);
		
		Place p02 = new Place(02, 0);
		Place p12 = new Place(12, 1);
		ArcZero a02 = new ArcZero(02, p02,t0);
		ArcZero a12 = new ArcZero(12, p02,t1);
		ArcZero a22 = new ArcZero(22, p12,t2);
		ArcZero a32 = new ArcZero(3, p02,t3);
		
		Place p03 = new Place(03, 100);
		Place p13 = new Place(13, 0);
		ArcEmptying a03 = new ArcEmptying(03, p03,t0);
		ArcEmptying a13 = new ArcEmptying(13, p03,t1);
		ArcEmptying a23 = new ArcEmptying(23, p03,t2);
		ArcEmptying a33 = new ArcEmptying(33, p13,t3);
		
		Assertions.assertTrue(t0.isDrawable());
		System.out.println(t1.isDrawable());
		Assertions.assertFalse(t1.isDrawable());
		Assertions.assertFalse(t2.isDrawable());
		Assertions.assertFalse(t3.isDrawable());
	}
}
