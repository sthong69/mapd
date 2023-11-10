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
		Assertions.assertEquals(0, p81.getNbTokens());
		Assertions.assertEquals(1, p82.getNbTokens());
		
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
		// RMD0
		PetriNet pn0 = new PetriNet("");
		Transition t0 = pn0.addTransition();
		Place p01 = pn0.addPlace(0);
		Arc a01 = pn0.addArc("in", 1, p01, t0);
		Place p02 = pn0.addPlace(0);
		Arc a02 = pn0.addArc("in", 1, p02, t0);
		pn0.fire();
		Assertions.assertEquals(0, p01.getNbTokens());
		Assertions.assertEquals(0, p02.getNbTokens());
		
		// RMD1
		PetriNet pn1 = new PetriNet("");
		Transition t1 = pn1.addTransition();
		Place p11 = pn1.addPlace(1);
		Arc a11 = pn1.addArc("in", 1, p11, t1);
		Place p12 = pn1.addPlace(0);
		Arc a12 = pn1.addArc("in", 1, p12, t1);
		pn1.fire();
		Assertions.assertEquals(1, p11.getNbTokens());
		Assertions.assertEquals(0, p12.getNbTokens());
		
		// RMD2
		PetriNet pn2 = new PetriNet("");
		Transition t2 = pn2.addTransition();
		Place p21 = pn2.addPlace(0);
		Arc a21 = pn2.addArc("in", 1, p21, t2);
		Place p22 = pn2.addPlace(1);
		Arc a22 = pn2.addArc("in", 1, p22, t2);
		pn2.fire();
		Assertions.assertEquals(0, p21.getNbTokens());
		Assertions.assertEquals(1, p22.getNbTokens());
		
		// RMD3
		PetriNet pn3 = new PetriNet("");
		Transition t3 = pn3.addTransition();
		Place p31 = pn3.addPlace(1);
		Arc a31 = pn3.addArc("in", 1, p31, t3);
		Place p32 = pn3.addPlace(1);
		Arc a32 = pn3.addArc("in", 1, p32, t3);
		pn3.fire();
		Assertions.assertEquals(0, p31.getNbTokens());
		Assertions.assertEquals(0, p32.getNbTokens());
		
		// RMD4
		PetriNet pn4 = new PetriNet("");
		Transition t4 = pn4.addTransition();
		Place p41 = pn4.addPlace(1);
		Arc a41 = pn4.addArc("in", 3, p41, t4);
		Place p42 = pn4.addPlace(1);
		Arc a42 = pn4.addArc("in", 1, p42, t4);
		pn4.fire();
		Assertions.assertEquals(1, p41.getNbTokens());
		Assertions.assertEquals(1, p42.getNbTokens());
		
		// RMD5
		PetriNet pn5 = new PetriNet("");
		Transition t5 = pn5.addTransition();
		Place p51 = pn5.addPlace(3);
		Arc a51 = pn5.addArc("in", 3, p51, t5);
		Place p52 = pn5.addPlace(1);
		Arc a52 = pn5.addArc("in", 1, p52, t5);
		pn5.fire();
		Assertions.assertEquals(0, p51.getNbTokens());
		Assertions.assertEquals(0, p52.getNbTokens());
		
		// RMV0
		PetriNet pn6 = new PetriNet("");
		Transition t6 = pn6.addTransition();
		Place p61 = pn6.addPlace(0);
		Arc a61 = pn6.addArc("emptying", p61, t6);
		Place p62 = pn6.addPlace(1);
		Arc a62 = pn6.addArc("in", 1, p62, t6);
		pn6.fire();
		Assertions.assertEquals(0, p61.getNbTokens());
		Assertions.assertEquals(1, p62.getNbTokens());
		
		// RMV1
		PetriNet pn7 = new PetriNet("");
		Transition t7 = pn7.addTransition();
		Place p71 = pn7.addPlace(3);
		Arc a71 = pn7.addArc("emptying", p71, t7);
		Place p72 = pn7.addPlace(1);
		Arc a72 = pn7.addArc("in", 1, p72, t7);
		pn7.fire();
		Assertions.assertEquals(0, p71.getNbTokens());
		Assertions.assertEquals(0, p72.getNbTokens());
		
		// RMZ0
		PetriNet pn8 = new PetriNet("");
		Transition t8 = pn8.addTransition();
		Place p81 = pn8.addPlace(1);
		Arc a81 = pn8.addArc("zero", p81, t8);
		Place p82 = pn8.addPlace(1);
		Arc a82 = pn8.addArc("in", 1, p82, t8);
		pn8.fire();
		Assertions.assertEquals(1, p81.getNbTokens());
		Assertions.assertEquals(1, p82.getNbTokens());
		
		// RMZ1
		PetriNet pn9 = new PetriNet("");
		Transition t9 = pn9.addTransition();
		Place p91 = pn9.addPlace(0);
		Arc a91 = pn9.addArc("zero", p91, t9);
		Place p92 = pn9.addPlace(1);
		Arc a92 = pn9.addArc("in", 1, p92, t9);
		pn9.fire();
		Assertions.assertEquals(0, p91.getNbTokens());
		Assertions.assertEquals(0, p92.getNbTokens());
		
		// RMM0
		PetriNet pn10 = new PetriNet("");
		Transition t10 = pn10.addTransition();
		Place p101 = pn10.addPlace(0);
		Arc a101 = pn10.addArc("in", 1, p101, t10);
		Place p102 = pn10.addPlace(0);
		Arc a102 = pn10.addArc("in", 1, p102, t10);
		Place p103 = pn10.addPlace(0);
		Arc a103 = pn10.addArc("out", 1, p103, t10);
		pn10.fire();
		Assertions.assertEquals(0, p101.getNbTokens());
		Assertions.assertEquals(0, p102.getNbTokens());
		Assertions.assertEquals(0, p103.getNbTokens());
		
		// RMM1
		PetriNet pn11 = new PetriNet("");
		Transition t11 = pn11.addTransition();
		Place p111 = pn11.addPlace(1);
		Arc a111 = pn11.addArc("in", 1, p111, t11);
		Place p112 = pn11.addPlace(0);
		Arc a112 = pn11.addArc("in", 1, p112, t11);
		Place p113 = pn11.addPlace(0);
		Arc a113 = pn11.addArc("out", 1, p113, t11);
		pn11.fire();
		Assertions.assertEquals(1, p111.getNbTokens());
		Assertions.assertEquals(0, p112.getNbTokens());
		Assertions.assertEquals(0, p113.getNbTokens());
		
		// RMM2
		PetriNet pn12 = new PetriNet("");
		Transition t12 = pn12.addTransition();
		Place p121 = pn12.addPlace(1);
		Arc a121 = pn12.addArc("in", 1, p121, t12);
		Place p122 = pn12.addPlace(1);
		Arc a122 = pn12.addArc("in", 1, p122, t12);
		Place p123 = pn12.addPlace(0);
		Arc a123 = pn12.addArc("out", 1, p123, t12);
		pn12.fire();
		Assertions.assertEquals(0, p121.getNbTokens());
		Assertions.assertEquals(0, p122.getNbTokens());
		Assertions.assertEquals(1, p123.getNbTokens());
		
		// RMM3
		PetriNet pn13 = new PetriNet("");
		Transition t13 = pn13.addTransition();
		Place p131 = pn13.addPlace(1);
		Arc a131 = pn13.addArc("in", 1, p131, t13);
		Place p132 = pn13.addPlace(1);
		Arc a132 = pn13.addArc("in", 1, p132, t13);
		Place p133 = pn13.addPlace(0);
		Arc a133 = pn13.addArc("out", 3, p133, t13);
		pn13.fire();
		Assertions.assertEquals(0, p131.getNbTokens());
		Assertions.assertEquals(0, p132.getNbTokens());
		Assertions.assertEquals(3, p133.getNbTokens());
	}
	
	@Test
	public void testArcDouble() throws Exception {
		// CAD0
		PetriNet pn0 = new PetriNet("");
		Transition t0 = pn0.addTransition();
		Place p0 = pn0.addPlace(0);
		Arc a0 = pn0.addArc("in", 10, p0, t0);
		
		Arc a0n = pn0.addArc("in", 5, p0, t0);
		
		Assertions.assertEquals(15, a0.getWeight());
		Assertions.assertEquals(1, pn0.getArcList().size());
		
		// CAD1e
		PetriNet pn1 = new PetriNet("");
		Transition t1 = pn1.addTransition();
		Place p1 = pn1.addPlace(0);
		Arc a1 = pn1.addArc("emptying", p1, t1);
		
		Exception exception1 = Assertions.assertThrows(Exception.class, ()->{Arc a1n = pn1.addArc("in", 5, p1, t1);});
		Assertions.assertEquals("Can't create an arc if there is already an existing zero/emptying one between the same place and transition.", exception1.getMessage());
		
		// CAD2e CAD3e
		PetriNet pn2 = new PetriNet("");
		Transition t2 = pn2.addTransition();
		Place p2 = pn2.addPlace(0);
		Arc a2 = pn2.addArc("in", 2, p2, t2);
		
		Exception exception2 = Assertions.assertThrows(Exception.class, ()->{Arc a2n = pn2.addArc("zero", p2, t2);});
		Assertions.assertEquals("Can't create an emptying/zero arc if there is already an existing arc between the same place and transition.", exception2.getMessage());
		
		Exception exception3 = Assertions.assertThrows(Exception.class, ()->{Arc a2n = pn2.addArc("emptying", p2, t2);});
		Assertions.assertEquals("Can't create an emptying/zero arc if there is already an existing arc between the same place and transition.", exception2.getMessage());
		
		// CAD4
		PetriNet pn4 = new PetriNet("");
		Transition t4 = pn4.addTransition();
		Place p4 = pn4.addPlace(0);
		Arc a4 = pn4.addArc("out", 10, p4, t4);
		
		Arc a4n = pn4.addArc("out", 5, p4, t4);
		
		Assertions.assertEquals(15, a4.getWeight());
		Assertions.assertEquals(1, pn4.getArcList().size());
		
		//CAD5e CAD6e
		PetriNet pn5 = new PetriNet("");
		Transition t5 = pn5.addTransition();
		Place p5 = pn5.addPlace(0);		
		Arc a5 = pn5.addArc("in", 5, p5, t5);
		
		Transition t6 = pn5.addTransition();
		Place p6 = pn5.addPlace(0);		
		Arc a6 = pn5.addArc("out", 5, p6, t6);
		
		Exception exception5 = Assertions.assertThrows(Exception.class, ()->{Arc an5 = pn5.addArc("out", 5, p5, t5);});
		Assertions.assertEquals("Can't create an arcOut if there is already an existing arcIn between the same place and transition.", exception5.getMessage());
		
		Exception exception6 = Assertions.assertThrows(Exception.class, ()->{Arc an6 = pn5.addArc("in", 5, p6, t6);});
		Assertions.assertEquals("Can't create an arcIn if there is already an existing arcOut between the same place and transition.", exception6.getMessage());
	}
}
