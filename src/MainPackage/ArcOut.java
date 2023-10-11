package MainPackage;

public class ArcOut extends Arc {

	public ArcOut(int weight, Place place, Transition transition) {
		super(weight, place, transition);
		updateTransition(transition);
	}
	
	private void updateTransition(Transition transition) {
	}

	public void startExchange() {
		this.getPlace().addTokens(this.getWeight());
	}

}
