package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import mainPackage.Place;



public class TestPlace {
	static Place p0;
	
	@BeforeAll
	public static void setup() {
		p0 = new Place(0);
	}
	
	@Test
	public void testConstructor() {
		p0 = new Place(0);
		Assertions.assertEquals(0,p0.getNbTokens());
		Assertions.assertEquals(0,p0.getArcList().size());
	}
	
	@Test
	public void testAddTokens() throws Exception {
		p0.addTokens(17);
		Assertions.assertEquals(17, p0.getNbTokens());
	}
}
