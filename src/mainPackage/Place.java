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
		if (nbTokensToAdd < 0) {
			throw new NegativeNbTokensException("La quantité de jetons à ajouter ne peut pas être négative !");
		}
		nbTokens += nbTokensToAdd;
	}
	
	public void removeTokens(int nbTokensToRemove) throws NegativeNbTokensException {
		if (nbTokensToRemove < 0) {
			throw new NegativeNbTokensException("La quantité de jetons à enlever ne peut pas être négative !");
		}
		if (nbTokensToRemove > nbTokens) {
			throw new NegativeNbTokensException("La quantité de jetons finale ne peut pas être négative !");
		}
		nbTokens -= nbTokensToRemove;
	}
	
	public boolean isEmpty() {
		return (nbTokens == 0);
	}
	
	public String toString() {
		return "";
	}
}
