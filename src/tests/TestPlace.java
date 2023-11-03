package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import Exceptions.NegativeNbTokensException;
import mainPackage.ArcIn;
import mainPackage.Place;



public class TestPlace {
	static Place p0;
	
	@BeforeAll
	public static void setup() {
		p0 = new Place(0);
	}
	
	@Test
	@Order(1)  
	public void testConstructor() {
		p0 = new Place(0);
		System.out.println(p0.getNbTokens());
		System.out.println(p0.getArcList().size());
		Assertions.assertEquals(0,p0.getNbTokens());
		Assertions.assertEquals(0,p0.getArcList().size());
	}
	
	@Test
	@Order(2)
	public void testAddTokens() throws Exception {
		p0.addTokens(17);
		Assertions.assertEquals(17, p0.getNbTokens());
		Assertions.assertThrows(NegativeNbTokensException.class, ()->p0.addTokens(-17));
	}
}
