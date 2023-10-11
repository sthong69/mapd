package MainPackage;

import java.util.LinkedList;public interface PetriNetInterface {
	public void fire();
	public void addArc(String type,int weight, Place p, Transition t) throws Exception;
	public void addArc(String type, Place p, Transition t) throws Exception;
	public void addPlace(int tokens);
	public void addTransition(LinkedList<ArcIn> arcIn, LinkedList<ArcOut> arcOut);
	public void removePlace(Place p);
	public void removeArc(Arc a);
	public void removeTransition(Transition t);
	public String toString();
}
