package MainPackage;

public class ArcIn extends Arc {

	public ArcIn(int weight, Place place, Transition transition) {
		super(weight, place, transition);
		updateTransition(transition);
	}
	
	public ArcIn(Place place, Transition transition) {
		super(place, transition);
		updateTransition(transition);
	}
	
	private void updateTransition(Transition transition) {
	}

	public void startExchange() throws Exception {
		this.getPlace().removeTokens(this.getWeight());
	}
	
	public boolean checkAvailability() {
		return (this.getWeight() <= this.getPlace().getNbTokens());
	}

}
