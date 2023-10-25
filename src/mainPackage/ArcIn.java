package mainPackage;

public class ArcIn extends Arc {

	public ArcIn(int weight, Place place, Transition transition) {
		setWeight(weight);
		setPlace(place);
		setTransition(transition);
		updateTransition(transition);
	}
	
	public ArcIn(Place place, Transition transition) {
		this(1, place, transition);
	}
	
	private void updateTransition(Transition transition) {
		transition.addArcInList(this);
	}

	public void startExchange() throws Exception {
		this.getPlace().removeTokens(this.getWeight());
	}
	
	public boolean checkAvailability() {
		return (this.getWeight() <= this.getPlace().getNbTokens());
	}

}
