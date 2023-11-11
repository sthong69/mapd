package mainPackage;

import Exceptions.NegativeWeightException;

/**
 * The 'ArcZero' class represents a specialized type of Arc that checks if the associated Place is empty. It does not contribute to token flow.
 * It can only be activated if the associated Place possesses exactly zero tokens.
 * It extends the 'ArcIn' class and overrides specific methods to implement its behavior.
 */
public class ArcZero extends ArcIn {
	
	/**
	 * Constructs a new ArcZero with specified Place and Transition.
	 * @param place The Place connected to this ArcZero.
	 * @param transition The Transition connected to this ArcZero.
	 */
	public ArcZero(Place place, Transition transition) throws NegativeWeightException{
		super(place, transition);
	}
	
	/**
	 * Starts the operation associated with this ArcZero by updating the associated Place's number of tokens.
	 * In this case, this method does nothing.
	 */
	public void startExchange() {
	}
	
	/**
	 * Determines if this ArcZero can operate.
	 * @return True if the associated Place has exactly zero tokens. False otherwise.
	 */	
	public boolean checkAvailability() {
		return (this.getPlace().getNbTokens()==0);
	}

}
