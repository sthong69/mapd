package tests;

import java.util.LinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Exceptions.NegativeWeightException;
import mainPackage.ArcIn;
import mainPackage.PetriNet;
import mainPackage.Place;
import mainPackage.Transition;

public class TestPetriNet {
	static PetriNet pn0;
	
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
		pn0.addPlace(0);
		Assertions.assertEquals(1,pn0.getPlaceList().size());
	}
	
	@Test
	public void testRemovePlace() {
		
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
