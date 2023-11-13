package mainPackage;

/**
 * The abstract 'Arc' class represents a connection between a Place and a Transition in the Petri net.
 * It defines methods for getting and setting associated attributes.
 */
public abstract class Arc {
	private String id;
	private int weight;
	private Place place;
	private Transition transition;


	/**
	 * Gets the weight associated to this Arc.
	 * @return The weight associated to this Arc.
	 */
	public int getWeight() { 
		return weight;
	}


	/**
	 * Sets the weight associated to this Arc.
	 * @param weight A weight to be associated with.
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	/**
	 * Sets the id associated to this Arc.
	 * @param id
	 */
	
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * Gets the Place associated to this Arc.
	 * @return The Place associated to this Arc.
	 */
	public Place getPlace() {
		return place;
	}


	/**
	 * Sets the Place associated to this Arc.
	 * @param place A Place to be associated with.
	 */
	public void setPlace(Place place) {
		this.place = place;
	}


	/**
	 * Gets the Transition associated to this Arc.
	 * @return The Transition associated to this Arc.
	 */
	public Transition getTransition() {
		return transition;
	}


	/**
	 * Sets the Transition associated to this Arc.
	 * @param transition A Transition to be associated with.
	 */
	public void setTransition(Transition transition) {
		this.transition = transition;
	}
	
	/**
	 * Returns a string representation of this Arc.
	 * @return A string representing this Arc.
	 */
	public String toString() {
		return "Arc: "+id+", Weight: "+weight+"Place: "+place.toString()+", Transition: "+transition.toString();
	}
}
