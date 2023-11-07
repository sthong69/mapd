package tests;

import java.util.LinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Exceptions.NegativeNbTokensException;
import Exceptions.NegativeWeightException;
import mainPackage.Arc;
import mainPackage.ArcEmptying;
import mainPackage.ArcIn;
import mainPackage.ArcOut;
import mainPackage.ArcZero;
import mainPackage.PetriNet;
import mainPackage.Place;
import mainPackage.Transition;

public class TestPetriNet {
	static PetriNet pn0;
	static Place pl0;
	static Transition t0;
	static Arc ai1;
	static Arc ao1;
	static Arc ae1;
	static Arc ae2;
	static Arc aempt;
	static Arc azero;
	static Arc ae3;
	
	@BeforeAll
	public static void setup() {
		pn0 = new PetriNet("Dummy PetriNet");
	}
	
	@Test  
	public void testConstructor() {
		pn0 = new PetriNet("Dummy PetriNet");
		Assertions.assertEquals("Dummy PetriNet", pn0.getName());
		Assertions.assertEquals(0, pn0.getArcList().size());
		Assertions.assertTrue(pn0.getArcList() instanceof LinkedList);
		Assertions.assertEquals(0, pn0.getPlaceList().size());
		Assertions.assertTrue(pn0.getPlaceList() instanceof LinkedList);
		Assertions.assertEquals(0, pn0.getTransitionList().size());
		Assertions.assertTrue(pn0.getTransitionList() instanceof LinkedList);
	}
	
	@Test
	public void testAddPlace() {
		pl0 = pn0.addPlace(2);
		Assertions.assertEquals(1,pn0.getPlaceList().size());
		Assertions.assertEquals(2, pl0.getNbTokens());
	}
	
	@Test
	public void testAddTransition() {
		t0 = pn0.addTransition();
		Assertions.assertEquals(1, pn0.getTransitionList().size());
	}
	
	@Test
	public void testAddArc() throws Exception, NegativeWeightException {
		
		ai1 = pn0.addArc("in", 2, pl0, t0);
		Assertions.assertTrue(ai1 instanceof ArcIn);
		Assertions.assertEquals(1, pn0.getArcList().size());
		
		ao1 = pn0.addArc("out", 3, pl0, t0);
		Assertions.assertTrue(ao1 instanceof ArcOut);
		Assertions.assertEquals(2, pn0.getArcList().size());
		
		NegativeWeightException exceptionNegative = Assertions.assertThrows(NegativeWeightException.class, ()-> ae1=pn0.addArc("in",-2, pl0, t0));
		Assertions.assertEquals("WARNING: An arc can not have a negative weight.", exceptionNegative.getMessage());
		
		Exception exceptionType1 = Assertions.assertThrows(Exception.class, ()-> ae2=pn0.addArc("ex",2, pl0, t0));
		Assertions.assertEquals("This type of arc does not exist/Not enough arguments", exceptionType1.getMessage());
		
		aempt = pn0.addArc("emptying", pl0, t0);
		Assertions.assertTrue(aempt instanceof ArcEmptying);
		Assertions.assertEquals(3, pn0.getArcList().size());
		
		azero = pn0.addArc("zero", pl0, t0);
		Assertions.assertTrue(azero instanceof ArcZero);
		Assertions.assertEquals(4, pn0.getArcList().size());
		
		Exception exceptionType2 = Assertions.assertThrows(Exception.class, ()-> ae3=pn0.addArc("ex", pl0, t0));
		Assertions.assertEquals("This type of arc does not exist/Not enough arguments", exceptionType2.getMessage());
	}
	
	@Test
	public void testRemoveTransition() throws NegativeWeightException, Exception {
		PetriNet pn1 = new PetriNet("");
		Place pl1 = pn1.addPlace(0);
		Transition t1 = pn1.addTransition();
		Assertions.assertEquals(1, pn1.getTransitionList().size());
		
		Arc a1 = pn1.addArc("in", 2, pl1, t1);
		Arc a2 = pn1.addArc("out", 2, pl1, t1);
		Assertions.assertEquals(2, pn1.getArcList().size());
		
		pn1.removeTransition(t1);
		Assertions.assertEquals(0, pn1.getArcList().size());
		Assertions.assertEquals(0, pn1.getTransitionList().size());
	}
	
	@Test
	public void testRemovePlace() throws NegativeWeightException, Exception {
		PetriNet pn1 = new PetriNet("");
		Place pl1 = pn1.addPlace(0);
		Transition t1 = pn1.addTransition();
		Assertions.assertEquals(1, pn1.getPlaceList().size());
		
		Arc a1 = pn1.addArc("in", 2, pl1, t1);
		Arc a2 = pn1.addArc("in", 1, pl1, t1);
		Arc a3 = pn1.addArc("in", 1, pl1, t1);
		Assertions.assertEquals(2, pn1.getArcList().size());
		
		pn1.removePlace(pl1);
		Assertions.assertEquals(0, pn1.getArcList().size());
		//System.out.println(pn1.getArcList());
		Assertions.assertEquals(0, pn1.getPlaceList().size());
	}
	
	@Test
	public void testRemoveArc() throws NegativeWeightException, Exception {
		PetriNet pn1 = new PetriNet("Test");
		
		Transition t1 = pn1.addTransition();
		Place pl1 = pn1.addPlace(2);
		
		Arc a1 = pn1.addArc("in", 1, pl1, t1);
		Arc a2 = pn1.addArc("out",4, pl1, t1);
		Arc a3 = pn1.addArc("emptying", pl1, t1);
		Arc a4 = pn1.addArc("zero", pl1, t1);
		
		Assertions.assertEquals(4, pl1.getArcList().size());
		Assertions.assertEquals(3, t1.getArcInList().size());
		Assertions.assertEquals(1, t1.getArcOutList().size());
		Assertions.assertEquals(4, pn1.getArcList().size());
		
		pn1.removeArc(a2);
		Assertions.assertEquals(0, t1.getArcOutList().size());
		Assertions.assertEquals(3, pl1.getArcList().size());
		Assertions.assertEquals(3, pn1.getArcList().size());
		
		pn1.removeArc(a1);
		Assertions.assertEquals(2, t1.getArcInList().size());
		Assertions.assertEquals(2, pl1.getArcList().size());
		Assertions.assertEquals(2, pn1.getArcList().size());
		
		pn1.removeArc(a3);
		Assertions.assertEquals(1, t1.getArcInList().size());
		Assertions.assertEquals(1, pl1.getArcList().size());
		Assertions.assertEquals(1, pn1.getArcList().size());
		
		pn1.removeArc(a4);
		Assertions.assertEquals(0, t1.getArcInList().size());
		Assertions.assertEquals(0, pl1.getArcList().size());
		Assertions.assertEquals(0, pn1.getArcList().size());
	}
	
	@Test
	public void testFireSingleEntry() throws Exception {
		// RD0
		PetriNet pn0 = new PetriNet("");
		Transition t0 = pn0.addTransition();
		Place p0 = pn0.addPlace(0);
		Arc a0 = pn0.addArc("in", 1, p0, t0);
		pn0.fire();
		Assertions.assertEquals(0, p0.getNbTokens());
		
		// RD1
		PetriNet pn1 = new PetriNet("");
		Transition t1 = pn1.addTransition();
		Place p1 = pn1.addPlace(2);
		Arc a1 = pn1.addArc("in", 1, p1, t1);
		pn1.fire();
		Assertions.assertEquals(1, p1.getNbTokens());
		
		// RD2
		PetriNet pn2 = new PetriNet("");
		Transition t2 = pn2.addTransition();
		Place p2 = pn2.addPlace(5);
		Arc a2 = pn2.addArc("in", 3, p2, t2);
		pn2.fire();
		Assertions.assertEquals(2, p2.getNbTokens());
		
		// RD3
		PetriNet pn3 = new PetriNet("");
		Transition t3 = pn3.addTransition();
		Place p3 = pn3.addPlace(2);
		Arc a3 = pn3.addArc("in", 3, p3, t3);
		pn3.fire();
		Assertions.assertEquals(2, p3.getNbTokens());
		
		// RG0
		PetriNet pn4 = new PetriNet("");
		Transition t4 = pn4.addTransition();
		Place p4 = pn4.addPlace(0);
		Arc a4 = pn4.addArc("out", 1, p4, t4);
		pn4.fire();
		Assertions.assertEquals(1, p4.getNbTokens());
		
		// RG1
		PetriNet pn5 = new PetriNet("");
		Transition t5 = pn5.addTransition();
		Place p5 = pn5.addPlace(1);
		Arc a5 = pn5.addArc("out", 1, p5, t5);
		pn5.fire();
		Assertions.assertEquals(2, p5.getNbTokens());
		
		// RG2
		PetriNet pn6 = new PetriNet("");
		Transition t6 = pn6.addTransition();
		Place p6 = pn6.addPlace(2);
		Arc a6 = pn6.addArc("out", 3, p6, t6);
		pn6.fire();
		Assertions.assertEquals(5, p6.getNbTokens());
		
		// RM0
		PetriNet pn7 = new PetriNet("");
		Transition t7 = pn7.addTransition();
		Place p71 = pn7.addPlace(0);
		Arc a71 = pn7.addArc("in", 1, p71, t7);
		Place p72 = pn7.addPlace(2);
		Arc a72 = pn7.addArc("out", 1, p72, t7);
		pn7.fire();
		Assertions.assertEquals(0, p71.getNbTokens());
		Assertions.assertEquals(2, p72.getNbTokens());
		
		// RM1
		PetriNet pn8 = new PetriNet("");
		Transition t8 = pn8.addTransition();
		Place p81 = pn8.addPlace(1);
		Arc a81 = pn8.addArc("in", 1, p81, t8);
		Place p82 = pn8.addPlace(0);
		Arc a82 = pn8.addArc("out", 1, p82, t8);
		pn8.fire();
		Assertions.assertEquals(0, p71.getNbTokens());
		Assertions.assertEquals(1, p72.getNbTokens());
		
		// RM2
		PetriNet pn9 = new PetriNet("");
		Transition t9 = pn9.addTransition();
		Place p91 = pn9.addPlace(0);
		Arc a91 = pn9.addArc("in", 1, p91, t9);
		Place p92 = pn9.addPlace(1);
		Arc a92 = pn9.addArc("out", 1, p92, t9);
		pn9.fire();
		Assertions.assertEquals(0, p91.getNbTokens());
		Assertions.assertEquals(1, p92.getNbTokens());
		
		// RM3
		PetriNet pn10 = new PetriNet("");
		Transition t10 = pn10.addTransition();
		Place p101 = pn10.addPlace(3);
		Arc a101 = pn10.addArc("in", 3, p101, t10);
		Place p102 = pn10.addPlace(0);
		Arc a102 = pn10.addArc("out", 1, p102, t10);
		pn10.fire();
		Assertions.assertEquals(0, p101.getNbTokens());
		Assertions.assertEquals(1, p102.getNbTokens());
		
		// RM4
		PetriNet pn11 = new PetriNet("");
		Transition t11 = pn11.addTransition();
		Place p111 = pn11.addPlace(3);
		Arc a111 = pn11.addArc("in", 1, p111, t11);
		Place p112 = pn11.addPlace(0);
		Arc a112 = pn11.addArc("out", 3, p112, t11);
		pn11.fire();
		Assertions.assertEquals(2, p111.getNbTokens());
		Assertions.assertEquals(3, p112.getNbTokens());
	}
	
	@Test
	public void testFireMultipleEntries() throws Exception {
		
	}
	
	@Test
	public void testArcDouble() throws Exception {
		// CAF0
		PetriNet pn0 = new PetriNet("");
		Transition t0 = pn0.addTransition();
		Place p0 = pn0.addPlace(0);
		Arc a0 = pn0.addArc("in", 10, p0, t0);
		
		Arc a0n = pn0.addArc("in", 5, p0, t0);
		
		Assertions.assertEquals(15, a0.getWeight());
		Assertions.assertEquals(1, pn0.getArcList().size());
		
		// CAF1e
		PetriNet pn1 = new PetriNet("");
		Transition t1 = pn1.addTransition();
		Place p1 = pn1.addPlace(0);
		Arc a1 = pn1.addArc("emptying", p1, t1);
		
		Exception exception1 = Assertions.assertThrows(Exception.class, ()->{Arc a1n = pn1.addArc("in", 5, p1, t1);});
		Assertions.assertEquals("Can't create an arc if there is already an existing zero/emptying one between the same place and transition.", exception1.getMessage());
		
		// CAF2e CAF3e
		PetriNet pn2 = new PetriNet("");
		Transition t2 = pn2.addTransition();
		Place p2 = pn2.addPlace(0);
		Arc a2 = pn2.addArc("in", p2, t2);
		
		Exception exception2 = Assertions.assertThrows(Exception.class, ()->{Arc a2n = pn2.addArc("zero", p2, t2);});
		Assertions.assertEquals("Can't create an emptying/zero arc if there is already an existing one between the same place and transition.", exception2.getMessage());
		
		Exception exception3 = Assertions.assertThrows(Exception.class, ()->{Arc a2n = pn2.addArc("emptying", p2, t2);});
		Assertions.assertEquals("Can't create an emptying/zero arc if there is already an existing one between the same place and transition.", exception2.getMessage());
		
		// CAF4
		PetriNet pn4 = new PetriNet("");
		Transition t4 = pn4.addTransition();
		Place p4 = pn4.addPlace(0);
		Arc a4 = pn4.addArc("out", 10, p4, t4);
		
		Arc a4n = pn4.addArc("in", 5, p4, t4);
		
		Assertions.assertEquals(15, a4.getWeight());
		Assertions.assertEquals(1, pn4.getArcList().size());
	}
}
