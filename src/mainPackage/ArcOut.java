package mainPackage;

import Exceptions.NegativeNbTokensException;
import Exceptions.NegativeWeightException;

public class ArcOut extends Arc {

	public ArcOut(int weight, Place place, Transition transition) throws NegativeWeightException{
		if (weight<0) {
			throw new NegativeWeightException("WARNING: An arc can not have a negative weight.");
		}
		setWeight(weight);
		setPlace(place);
		setTransition(transition);
		updateTransition(transition);
	}
	
	public ArcOut(Place place, Transition transition) throws NegativeWeightException{
		this(1, place, transition);
	}
	
	private void updateTransition(Transition transition) {
		transition.addArcOut(this);
	}

	public void startExchange() throws NegativeNbTokensException{
		this.getPlace().addTokens(this.getWeight());
	}

}