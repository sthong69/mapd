package mainPackage;

import Exceptions.NegativeNbTokensException;

/**
 * The 'PetriNetInterface' interface defines the methods that a Petri net network should implement.
 * It provides a set of methods to operate, manipulate and analyze the network.
 */
public interface PetriNetInterface {
	
	/**
	 * Fires a step in the network: it draws a random possible Transition of the network and proceeds with its execution.
	 */
	public void fire() throws Exception;
	
	/**
	 * Adds a designated type of Arc in this PetriNet, associated with a Place and a Transition.
	 * It also starts the verification and treatment process ensuring that no "double Arc" are existing in the network.
	 * @param type The wanted type of the Arc to be created. This method processes two types : "in" and "out".
	 * @param weight The weight to be associated with the created Arc.
	 * @param place The Place to be associated with the created Arc.
	 * @param transition The Transition to be associated with the created Arc.
	 */
	public Arc addArc(String type,int weight, Place p, Transition t) throws Exception;
	
	/**
	 * Adds a designated type of Arc in this PetriNet, associated with a Place and a Transition.
	 * It also starts the verification and treatment process ensuring that no "double Arc" are existing in the network.
	 * @param type The wanted type of the Arc to be created. This method processes two types : "emptying" and "zero".
	 * @param place The Place to be associated with the created Arc.
	 * @param transition The Transition to be associated with the created Arc.
	 */
	public Arc addArc(String type, Place p, Transition t) throws Exception;
	
	/**
	 * Adds a Place with a designated number of tokens to the network.
	 * @param tokens The number of tokens of the added Place.
	 * @throws NegativeNbTokensException 
	 */
	public Place addPlace(int tokens) throws NegativeNbTokensException;
	
	/**
	 * Adds a Transition to the network.
	 */
	public Transition addTransition();
	
	/**
	 * Removes a Place of the network. It ensures that the associated Arc are correctly updated.
	 * @param place The Place to be removed of the network.
	 */
	public void removePlace(Place p);
	
	/**
	 * Removes an Arc of the network. It ensures that the associated Place and Transition are correctly updated.
	 * @param arc The Arc to be removed of the network.
	 */
	public void removeArc(Arc a);
	
	/**
	 * Removes a Transition of the network. It ensures that the Transition is correctly updated.
	 * @param place The Transition to be removed of the network.
	 */
	public void removeTransition(Transition t);
	
	
	public String toString();
}
