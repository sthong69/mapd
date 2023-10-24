package MainPackage;

public class ArcZero extends ArcIn {

	public ArcZero(Place place, Transition transition) {
		super(place, transition);
	}
	
	public void startExchange() {
	}
	
	public boolean checkAvailability() {
		return (this.getPlace().getNbTokens()==0);
	}

}
