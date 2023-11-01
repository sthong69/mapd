package mainPackage;

import Exceptions.NegativeNbTokensException;
import Exceptions.NegativeWeightException;

public class ArcOut extends Arc {

	public ArcOut(int weight, Place place, Transition transition) throws NegativeWeightException{
		if (weight<0) {
			throw new NegativeWeightException("warning : negative weight");
		}
		setWeight(weight);
		setPlace(place);
		setTransition(transition);
		updateTransition(transition);
	}
	
	private void updateTransition(Transition transition) {
		transition.addArcOutList(this);
	}

	public void startExchange() throws NegativeNbTokensException{
		this.getPlace().addTokens(this.getWeight());
	}

}