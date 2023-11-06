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
	public void testRemoveTransition() {
		
	}
	
	@Test
	public void testRemovePlace() {
		
	}
	
	@Test
	public void testRemoveArc() throws NegativeWeightException, Exception {
		Transition t1 = pn0.addTransition();
		Place pl1 = pn0.addPlace(2);
		
		Arc a1 = pn0.addArc("in", 1, pl1, t1);
		Arc a2 = pn0.addArc("out",4, pl1, t1);
		Arc a3 = pn0.addArc("emptying", pl1, t1);
		Arc a4 = pn0.addArc("zero", pl1, t1);
		
		Assertions.assertEquals(4, pl1.getArcList().size());
		Assertions.assertEquals(3, t1.getArcInList().size());
		Assertions.assertEquals(1, t1.getArcOutList().size());
		
		pn0.removeArc(a2);
		Assertions.assertEquals(0, t1.getArcOutList().size());
		Assertions.assertEquals(3, pl1.getArcList().size());
		
		/*pn0.removeArc(a1);
		Assertions.assertEquals(2, t1.getArcInList().size());
		Assertions.assertEquals(2, pl1.getArcList().size());
		
		pn0.removeArc(a3);
		Assertions.assertEquals(1, t1.getArcInList().size());
		Assertions.assertEquals(1, pl1.getArcList().size());
		
		pn0.removeArc(a4);
		Assertions.assertEquals(0, t1.getArcInList().size());
		Assertions.assertEquals(0, pl1.getArcList().size());*/
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
