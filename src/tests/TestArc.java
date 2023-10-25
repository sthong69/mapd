package tests;

import java.util.LinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import mainPackage.Arc;
import mainPackage.ArcIn;
import mainPackage.Place;
import mainPackage.Transition;

public class TestArc {
	
	static Place p0;
	static Transition t0;
	static Arc a0;
	static Arc a1;
	static LinkedList<Arc> testList;
	
	@BeforeAll
	public static void setup() {
		p0 = new Place (0);
		t0 = new Transition();
		a0 = new ArcIn(5, p0, t0);
		a1 = new ArcIn(p0, t0);
		testList = new LinkedList<Arc>();
		testList.add(a0);
	}
	
	@Test
	public void testConstructor() {
		Assertions.assertEquals(p0, a0.getPlace());
		Assertions.assertEquals(p0, a1.getPlace());
		System.out.println(a1.getWeight());
		Assertions.assertEquals(2, t0.getArcInList().size());
		Assertions.assertEquals(a0, t0.getArcInList().get(0));
	}
	
	
	
}
