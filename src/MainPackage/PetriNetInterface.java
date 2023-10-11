package MainPackage;

import java.util.LinkedList;public interface PetriNetInterface {
	public void fire();
	public void addArc(int weight, Place p, Transition t);
	public void addPlace(int tokens);
	public void addTransition(LinkedList<ArcIn> arcIn, LinkedList<ArcOut> arcOut);
	public void removePlace(Place p);
	public void removeArc(Arc a);
	public void removeTransition(Transition t);
	public String toString();
	
	
}
