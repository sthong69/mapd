package mainPackage;

public class ArcOut extends Arc {

	public ArcOut(int weight, Place place, Transition transition) {
		setWeight(weight);
		setPlace(place);
		setTransition(transition);
		updateTransition(transition);
	}
	
	private void updateTransition(Transition transition) {
		transition.addArcOutList(this);
	}

	public void startExchange() {
		this.getPlace().addTokens(this.getWeight());
	}

}