package mainPackage;

import Exceptions.NegativeWeightException;

public class ArcZero extends ArcIn {

	public ArcZero(Place place, Transition transition) throws NegativeWeightException{
		super(place, transition);
	}
	
	public void startExchange() {
	}
	
	public boolean checkAvailability() {
		return (this.getPlace().getNbTokens()==0);
	}

}
