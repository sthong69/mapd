package mainPackage;

import java.util.LinkedList;

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
	
	public void addTokens(int nbTokensToAdd) throws Exception {
		if (nbTokensToAdd<0) {
			throw new Exception("La quantité de jetons à ajouter ne peut pas être négative !");
		}
		nbTokens += nbTokensToAdd;
	}
	
	public void removeTokens(int nbTokensToRemove) throws Exception {
		if (nbTokensToRemove < nbTokens) {
			throw new Exception("Pas assez de jetons.");
		}
		nbTokens -= nbTokensToRemove;
	}
	
	public boolean isEmpty() {
		return (nbTokens == 0);
	}
	
	public String toString() {
		return "Place String";
	}
	
}
