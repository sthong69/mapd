package mainPackage;

import java.util.LinkedList;

import Exceptions.NegativeNbTokensException;

public class Place {
	private int nbTokens;
	private LinkedList<Arc> arcList;
	
	public Place(int nbTokens) {
		setnbTokens(nbTokens);
		setArcList();
	}
	
	private void setnbTokens(int nbTokens) {
		this.nbTokens = nbTokens;
	}
	
	private void setArcList() {
		this.arcList = new LinkedList<Arc>();
	}
	
	public int getNbTokens() {
		return nbTokens;
	}
	
	public LinkedList<Arc> getArcList() {
		return arcList;
	}
	
	public void addTokens(int nbTokensToAdd) throws NegativeNbTokensException {
		if (nbTokensToAdd<0) {
			throw new NegativeNbTokensException("La quantité de jetons à ajouter ne peut pas être négative !");
		}
		nbTokens += nbTokensToAdd;
	}
	
	public void removeTokens(int nbTokensToRemove) throws Exception {
		if (nbTokensToRemove > nbTokens) {
			throw new Exception("Pas assez de jetons.");
		}
		nbTokens -= nbTokensToRemove;
	}
	
	public boolean isEmpty() {
		return (nbTokens == 0);
	}
	
	public String toString() {
		return "";
	}
	
	public static void main(String[] args) {
		Place p0 = new Place(2);
		System.out.println(p0.toString());
	}
	
}
