package tests;

import java.util.LinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
		PetriNet pn = new PetriNet("");
		Place p1 = new Place (0);
		Place p2 = new Place (0);
		Place p3 = new Place (0);
		Place p4 = new Place (0);
		Place p5 = new Place (0);
		Place p6 = new Place (0);
		Place p7 = new Place (0);
		Transition t = new Transition();
		
		ai1 = pn.addArc("in", 2, p1, t);
		Assertions.assertTrue(ai1 instanceof ArcIn);
		Assertions.assertEquals(1, pn.getArcList().size());
		
		ao1 = pn.addArc("out", 3, p2, t);
		Assertions.assertTrue(ao1 instanceof ArcOut);
		Assertions.assertEquals(2, pn.getArcList().size());
		
		NegativeWeightException exceptionNegative = Assertions.assertThrows(NegativeWeightException.class, ()-> ae1=pn.addArc("in",-2, p3, t));
		Assertions.assertEquals("WARNING: An arc can not have a negative weight.", exceptionNegative.getMessage());
		
		Exception exceptionType1 = Assertions.assertThrows(Exception.class, ()-> ae2=pn.addArc("ex",2, p4, t));
		Assertions.assertEquals("This type of arc does not exist/Not enough arguments", exceptionType1.getMessage());
		
		aempt = pn.addArc("emptying", p5, t);
		Assertions.assertTrue(aempt instanceof ArcEmptying);
		Assertions.assertEquals(3, pn.getArcList().size());
		
		azero = pn.addArc("zero", p6, t);
		Assertions.assertTrue(azero instanceof ArcZero);
		Assertions.assertEquals(4, pn.getArcList().size());
		
		Exception exceptionType2 = Assertions.assertThrows(Exception.class, ()-> ae3=pn.addArc("ex", p7, t));
		Assertions.assertEquals("This type of arc does not exist/Not enough arguments", exceptionType2.getMessage());
	}
	
	@Test
	public void testRemoveTransition() throws NegativeWeightException, Exception {
		PetriNet pn1 = new PetriNet("");
		Place pl1 = pn1.addPlace(0);
		Place pl2 = pn1.addPlace(0);
		Transition t1 = pn1.addTransition();
		Assertions.assertEquals(1, pn1.getTransitionList().size());
		
		Arc a1 = pn1.addArc("in", 2, pl1, t1);
		Arc a2 = pn1.addArc("out", 2, pl2, t1);
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
		Transition t2 = pn1.addTransition();
		Assertions.assertEquals(1, pn1.getPlaceList().size());
		
		Arc a1 = pn1.addArc("in", 2, pl1, t1);
		Arc a2 = pn1.addArc("in", 1, pl1, t2);
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
		Place p1 = pn1.addPlace(2);
		Place p2 = pn1.addPlace(2);
		Place p3 = pn1.addPlace(2);
		Place p4 = pn1.addPlace(2);
		
		Arc a1 = pn1.addArc("in", 1, p1, t1);
		Arc a2 = pn1.addArc("out",4, p2, t1);
		
		Assertions.assertEquals(1, p1.getArcList().size());
		Assertions.assertEquals(1, p2.getArcList().size());
		Assertions.assertEquals(1, t1.getArcInList().size());
		Assertions.assertEquals(1, t1.getArcOutList().size());
		Assertions.assertEquals(2, pn1.getArcList().size());
		
		pn1.removeArc(a2);
		Assertions.assertEquals(0, t1.getArcOutList().size());
		Assertions.assertEquals(0, p2.getArcList().size());
		Assertions.assertEquals(1, pn1.getArcList().size());
		
		pn1.removeArc(a1);
		Assertions.assertEquals(0, t1.getArcInList().size());
		Assertions.assertEquals(0, p1.getArcList().size());
		Assertions.assertEquals(0, pn1.getArcList().size());

	}
	
	@Test
	public void testSingleEntryTransition() throws NegativeWeightException {
		// RD0
		Transition t0 = new Transition();
		Place p0 = new Place(0);
		ArcIn a0 = new ArcIn(p0,t0);
		
	}
	
	@Test
	public void testMultipleEntriesTransition() {
		
	}
}
