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
			throw new NegativeNbTokensException("WARNING: The number of tokens to add can not be negative.");
		}
		nbTokens += nbTokensToAdd;
	}
	
	public void removeTokens(int nbTokensToRemove) throws NegativeNbTokensException {
		if (nbTokensToRemove < 0) {
			throw new NegativeNbTokensException("WARNING: The number of tokens to remove can not be negative.");
		}
		if (nbTokensToRemove > nbTokens) {
			throw new NegativeNbTokensException("WARNING: The final number of tokens can not be negative.");
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
