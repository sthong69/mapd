package mainPackage;

import Exceptions.NegativeNbTokensException;
import Exceptions.NegativeWeightException;

/**
 * The 'ArcEmptying' class represents a specialized type of Arc that drains tokens from a Place.
 * It can only be activated if the associated Place possesses at least one token.
 * It extends the 'ArcIn' class and overrides specific methods to implement its behavior.
 */
public class ArcEmptying extends ArcIn {

	/**
	 * Constructs a new EmptyingArc with specified Place and Transition.
	 * @param id The id associated to this ArcEmptying.
	 * @param place The Place connected to this DrainerArc.
	 * @param transition The Transition connected to this DrainerArc.
	 */
	public ArcEmptying(int id, Place place, Transition transition) throws NegativeWeightException{
		super(id, place, transition);
	}
	
	/**
	 * Starts the operation associated with this ArcEmptying by updating the associated Place's number of tokens.
	 * In this case, the number of tokens removed is equal to the Place's number of tokens.
	 */
	public void startExchange() throws NegativeNbTokensException {
		this.getPlace().removeTokens(this.getPlace().getNbTokens());
	}
	
	/**
	 * Determines if this EmptyingArc can operate.
	 * @return True if the associated Place has at least one token. False otherwise.
	 */	
	public boolean checkAvailability() {
		return (this.getPlace().getNbTokens()!=0);
	}

}
