package mainPackage;

import java.util.LinkedList;

import Exceptions.NegativeNbTokensException;

/**
 * The 'Place' class represents a location in a Petri net where tokens can be stored.
 * It contains methods for managing the number of tokens and associated arcs.
 */
public class Place {
	private int nbTokens;
	private LinkedList<Arc> arcList;
	
	/**
	 * Constructs a new Place with a specified initial number of tokens.
	 * @param nbTokens The initial number of tokens in the place.
	 */	
	public Place(int nbTokens) {
		setnbTokens(nbTokens);
		setArcList(new LinkedList<Arc>());
	}
	
	/**
	 * Sets the number of tokens of this Place.
	 * @param nbTokens The number of tokens to be associated with this Place.
	 */
	private void setnbTokens(int nbTokens) {
		this.nbTokens = nbTokens;
	}
	
	/**
	 * Sets the list of Arc of this Place.
	 * @param arcList The list of Arc to be associated with this Place.
	 */
	private void setArcList(LinkedList<Arc> arcList) {
		this.arcList = arcList;
	}
	
	/**
	 * Gets the number of tokens of this Place.
	 * @return The number of tokens of this Place.
	 */
	public int getNbTokens() {
		return nbTokens;
	}
	
	/**
	 * Gets the list of Arc of this Place.
	 * @return The list of Arc of this Place.
	 */
	public LinkedList<Arc> getArcList() {
		return arcList;
	}
	
	/**
	 * Adds a specified number of tokens to the number of tokens of this Place.
	 * @param nbTokensToAdd The number of tokens to add.
	 * @throws NegativeNbTokensException
	 */
	public void addTokens(int nbTokensToAdd) throws NegativeNbTokensException {
		if (nbTokensToAdd < 0) {
			throw new NegativeNbTokensException("WARNING: The number of tokens to add can not be negative.");
		}
		nbTokens += nbTokensToAdd;
	}
	
	/**
	 * Removes a specified number of tokens from the number of tokens of this Place.
	 * @param nbTokensToAdd The number of tokens to add.
	 * @throws NegativeNbTokensException
	 */
	public void removeTokens(int nbTokensToRemove) throws NegativeNbTokensException {
		if (nbTokensToRemove < 0) {
			throw new NegativeNbTokensException("WARNING: The number of tokens to remove can not be negative.");
		}
		if (nbTokensToRemove > nbTokens) {
			throw new NegativeNbTokensException("WARNING: The final number of tokens can not be negative.");
		}
		nbTokens -= nbTokensToRemove;
	}
	
	/**
	 * Adds an Arc to the list of Arc of this Place.
	 * @param arc The arc to add to the list of Arc of this Place.
	 */
	public void addArc(Arc arc) {
		this.arcList.add(arc);
	}
	
	/**
	 * Removes an Arc from the list of Arc of this Place.
	 * @param arc The arc to remove from the list of Arc of this Place.
	 */
	public void removeArc(Arc arc) {
		this.arcList.remove(arc);
	}
	
	/**
	 * Checks whether or not the number of tokens of this Place is equal to 0.
	 * @return True if the number of tokens of this Place is equal to 0. False otherwise.
	 */
	public boolean isEmpty() {
		return (nbTokens == 0);
	}
	
	/**
	 * Returns a string representation of this Place.
	 * @return A string representing this Place.
	 */
	public String toString() {
		return "";
	}
}
