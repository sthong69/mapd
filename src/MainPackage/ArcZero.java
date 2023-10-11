package MainPackage;

public class ArcZero extends ArcIn {

	public ArcZero(int weight, Place place, Transition transition) {
		super(weight, place, transition);
	}
	
	public void startExchange() {
	}
	
	public boolean checkAvailability() {
		return (this.getPlace().getNbTokens()==0);
	}

}
