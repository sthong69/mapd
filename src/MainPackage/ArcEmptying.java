package MainPackage;

public class ArcEmptying extends ArcIn {

	public ArcEmptying(int weight, Place place, Transition transition) {
		super(weight, place, transition);
	}
	
	public void startExchange() throws Exception {
		this.getPlace().removeTokens(this.getPlace().getNbTokens());
	}
	
	public boolean checkAvailability() {
		return (this.getPlace().getNbTokens()!=0);
	}

}
