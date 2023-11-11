package mainPackage;

import Exceptions.NegativeNbTokensException;
import Exceptions.NegativeWeightException;

/**
 * The 'ArcIn' class represents the type of Arc that add tokens to a Place.
 * It can only be activated if all ArcIn of the associated transition are able to operate.
 * It extends the abstract 'Arc' class.
 */
public class ArcOut extends Arc {

	/**
	 * Constructs a new ArcOut with specified weight, Place and Transition.
	 * @param weight The weight associated to this ArcOut.
	 * @param place The Place connected to this ArcOut.
	 * @param transition The Transition connected to this ArcOut.
	 */
	public ArcOut(int weight, Place place, Transition transition) throws NegativeWeightException{
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
	 * Constructs a new ArcOut with specified Place and Transition and without specifying the weight (set to 1).
	 * @param place The Place connected to this ArcOut.
	 * @param transition The Transition connected to this ArcOut.
	 */
	public ArcOut(Place place, Transition transition) throws NegativeWeightException{
		this(1, place, transition);
	}
	
	/**
	 * Updates the list of ArcOut of a designated Transition.
	 * @param transition The Transition whose list of ArcOut needs to be updated.
	 */
	private void updateTransition(Transition transition) {
		transition.addArcOut(this);
	}
	
	/**
	 * Updates the list of Arc of a designated Place.
	 * @param place The Place whose list of Arc needs to be updated.
	 */
	private void updatePlace(Place place) {
		place.addArc(this);
	}

	/**
	 * Starts the operation associated with this ArcOut by updating the associated Place's number of tokens.
	 * In this case, the number of tokens added is equal to the ArcOut's weight.
	 */
	public void startExchange() throws NegativeNbTokensException{
		this.getPlace().addTokens(this.getWeight());
	}

}