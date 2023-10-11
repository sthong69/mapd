package MainPackage;

public class Arc {
	private int weight;
	private Place place;
	private Transition transition;
	
	
	public Arc(int weight, Place place, Transition transition) {
		setWeight(weight);
		setPlace(place);
		setTransition(transition);
	}


	public Arc(Place place, Transition transition) {
		setPlace(place);
		setTransition(transition);
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}


	public Place getPlace() {
		return place;
	}


	public void setPlace(Place place) {
		this.place = place;
	}


	public Transition getTransition() {
		return transition;
	}


	public void setTransition(Transition transition) {
		this.transition = transition;
	}
	
	public String toString() {
		return "Arc String";
	}
}
