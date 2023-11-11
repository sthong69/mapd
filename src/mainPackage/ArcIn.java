package mainPackage;

import Exceptions.NegativeNbTokensException;
import Exceptions.NegativeWeightException;

/**
 * The 'ArcIn' class represents the type of Arc that remove tokens from a Place.
 * It can only be activated if the associated Place possesses a number of tokens greater than the ArcIn's weight.
 * It extends the abstract 'Arc' class.
 */
public class ArcIn extends Arc {

	/**
	 * Constructs a new ArcIn with specified weight, Place and Transition.
	 * @param weight The weight associated to this ArcIn.
	 * @param place The Place connected to this ArcIn.
	 * @param transition The Transition connected to this ArcIn.
	 */
	public ArcIn(int weight, Place place, Transition transition) throws NegativeWeightException{
		if (weight<0) {
			throw new NegativeWeightException("WARNING: An arc can not have a negative weight.");
		}
		
		setWeight(weight);
		setPlace(place);
		setTransition(transition);
		updateTransition(transition);
		updatePlace(place);
	}
	
	/**
	 * Constructs a new ArcIn with specified Place and Transition and without specifying the weight (set to 1).
	 * @param place The Place connected to this ArcIn.
	 * @param transition The Transition connected to this ArcIn.
	 */
	public ArcIn(Place place, Transition transition) throws NegativeWeightException{
		this(1, place, transition);
	}
	
	/**
	 * Updates the list of ArcIn of a designated Transition.
	 * @param transition The Transition whose list of ArcIn needs to be updated.
	 */
	private void updateTransition(Transition transition) {
		transition.addArcIn(this);
	}
	
	/**
	 * Updates the list of Arc of a designated Place.
	 * @param place The Place whose list of Arc needs to be updated.
	 */
	private void updatePlace(Place place) {
		place.addArc(this);
	}
	
	/**
	 * Starts the operation associated with this ArcIn by updating the associated Place's number of tokens.
	 * In this case, the number of tokens removed is equal to the ArcIn's weight.
	 */
	public void startExchange() throws NegativeNbTokensException {
		this.getPlace().removeTokens(this.getWeight());
	}
	
	/**
	 * Determines if this ArcIn can operate.
	 * @return True if the associated Place's number of tokens is greater than the ArcIn's weight. False otherwise.
	 */
	public boolean checkAvailability() {
		return (this.getWeight() <= this.getPlace().getNbTokens());
	}
}
