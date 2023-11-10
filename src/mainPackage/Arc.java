package mainPackage;

/**
 * The abstract 'Arc' class represents a connection between a Place and a Transition in the Petri net.
 * It defines methods for getting and setting associated attributes.
 */
public abstract class Arc {
	private int weight;
	private Place place;
	private Transition transition;


	/**
	 * @return weight
	 */
	public int getWeight() { 
		return weight;
	}


	/**
	 * @param weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}


	/**
	 * @return
	 */
	public Place getPlace() {
		return place;
	}


	/**
	 * @param place
	 */
	public void setPlace(Place place) {
		this.place = place;
	}


	/**
	 * Get the transition associated to this Arc.
	 * @return The transition associated to this Arc.
	 */
	public Transition getTransition() {
		return transition;
	}


	/**
	 * Set the transition associated to this Arc.
	 * @param A transition to be associated with.
	 */
	public void setTransition(Transition transition) {
		this.transition = transition;
	}
	
	/**
	 * Returns a string representation of this Arc.
	 * @return A string representing this Arc.
	 */
	public String toString() {
		return "Weight: "+weight+"\nPlace: "+place+"\nTransition: "+transition;
	}
}
