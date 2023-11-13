package mainPackage;

import java.util.LinkedList;
import java.util.List;

/**
 * The 'Transition' class represents a state in a Petri net where a firing event can occur.
 * It contains methods to manage the arcs associated with the transition and to control its behavior.
 */
public class Transition {
	
	private String id;
	private LinkedList<ArcIn> arcInList;
	private LinkedList<ArcOut> arcOutList;
	
	/**
	 * Constructs a new Transition with empty lists of ArcIn and ArcOut.
	 * @param id The id associated to this Transition.
	 */
	public Transition(int id) {
		setId("T_"+id);
		setArcInList(new LinkedList<ArcIn>());
		setArcOutList(new LinkedList<ArcOut>());
	}
	
	/**
	 * Gets the list of ArcIn of this Transition.
	 * @return The list of ArcIn of this Transition.
	 */
	public LinkedList<ArcIn> getArcInList() {
		return arcInList;
	}
	
	/**
	 * Sets the list of ArcIn of this Transition.
	 * @param arcInList The list of ArcIn to be associated with this Transition.
	 */
	public void setArcInList(LinkedList<ArcIn> arcInList) {
		this.arcInList = arcInList;
	}
	
	/**
	 * Sets the id associated to this Transition.
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Gets the list of ArcOut of this Transition.
	 * @return The list of ArcOut of this Transition.
	 */
	public LinkedList<ArcOut> getArcOutList() {
		return arcOutList;
	}
	
	/**
	 * Sets the list of ArcOut of this Transition.
	 * @param arcOutList The list of ArcOut to be associated with this Transition.
	 */
	public void setArcOutList(LinkedList<ArcOut> arcOutList) {
		this.arcOutList = arcOutList;
	}
	
	/**
	 * Adds an ArcIn to this Transition.
	 * @param arc The ArcIn to be added to this Transition.
	 */
	public void addArcIn(ArcIn arc) {
		this.arcInList.add(arc);
	}
	
	/**
	 * Removes an ArcIn from this Transition.
	 * @param arc The ArcIn to be removed from this Transition.
	 */
	public void removeArcIn(ArcIn arc) {
		this.arcInList.remove(arc);
	}
	
	/**
	 * Adds an ArcOut to this Transition.
	 * @param arc The ArcOut to be added to this Transition.
	 */
	public void addArcOut(ArcOut arc) {
		this.arcOutList.add(arc);
	}
	
	/**
	 * Removes an ArcOut from this Transition.
	 * @param arc The ArcOut to be removed from this Transition.
	 */
	public void removeArcOut(ArcOut arc) {
		this.arcOutList.remove(arc);
	}
	
	/**
	 * Checks whether or not this Transition can be drawn and executed based on its list of ArcIn.
	 * @return True if each ArcIn of the Transition can perform their operation. False otherwise.
	 */
	public boolean isDrawable() {
		boolean test;
		for (ArcIn arc : this.arcInList) {
			test = arc.checkAvailability();
			if (!test) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns a string representation of this Transition.
	 * @return A string representing this Transition.
	 */
	public String toString() {
		return "Transition: "+id;
	}
	
}
