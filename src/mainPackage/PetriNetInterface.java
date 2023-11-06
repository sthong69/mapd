package mainPackage;

public interface PetriNetInterface {
	public void fire() throws Exception;
	public Arc addArc(String type,int weight, Place p, Transition t) throws Exception;
	public Arc addArc(String type, Place p, Transition t) throws Exception;
	public Place addPlace(int tokens);
	public Transition addTransition();
	public void removePlace(Place p);
	public void removeArc(Arc a);
	public void removeTransition(Transition t);
	public String toString();
}
