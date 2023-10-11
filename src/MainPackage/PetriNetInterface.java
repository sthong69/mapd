package MainPackage;

public interface PetriNetInterface {
	public void fire() throws Exception;
	public void addArc(String type,int weight, Place p, Transition t) throws Exception;
	public void addArc(String type, Place p, Transition t) throws Exception;
	public void addPlace(int tokens);
	public void addTransition();
	public void removePlace(Place p);
	public void removeArc(Arc a);
	public void removeTransition(Transition t);
	public String toString();
}
