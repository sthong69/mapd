package mainPackage;

import Exceptions.NegativeNbTokensException;
import Exceptions.NegativeWeightException;

public class ArcEmptying extends ArcIn {

	public ArcEmptying(Place place, Transition transition) throws NegativeWeightException{
		super(place, transition);
	}
	
	public void startExchange() throws NegativeNbTokensException {
		this.getPlace().removeTokens(this.getPlace().getNbTokens());
	}
	
	public boolean checkAvailability() {
		return (this.getPlace().getNbTokens()!=0);
	}

}
