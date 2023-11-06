package mainPackage;

import Exceptions.NegativeNbTokensException;
import Exceptions.NegativeWeightException;

public class ArcIn extends Arc {

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
	
	public ArcIn(Place place, Transition transition) throws NegativeWeightException{
		this(1, place, transition);
	}
	
	private void updateTransition(Transition transition) {
		transition.addArcIn(this);
	}
	
	private void updatePlace(Place place) {
		place.addArc(this);
	}

	public void startExchange() throws NegativeNbTokensException {
		this.getPlace().removeTokens(this.getWeight());
	}
	
	public boolean checkAvailability() {
		return (this.getWeight() <= this.getPlace().getNbTokens());
	}

}
