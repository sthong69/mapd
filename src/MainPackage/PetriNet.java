package MainPackage;

import java.util.LinkedList;

public class PetriNet implements PetriNetInterface {
	
	private String name;
	private LinkedList<Arc> arcList;
	private LinkedList<Place> placeList;
	private LinkedList<Transition> transitionList;
	
	public PetriNet(String name) {
		this.name=name;
		this.arcList = new LinkedList<Arc>();
		this.placeList = new LinkedList<Place>();
		this.transitionList = new LinkedList<Transition>();
	}

	public void fire() {
			
	}

	public void addArc(int weight, Place p, Transition t) {
		
		Arc a = new Arc(weight, p, t);
	}

	@Override
	public void addPlace(int tokens) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTransition(LinkedList<ArcIn> arcIn, LinkedList<ArcOut> arcOut) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePlace(Place p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeArc(Arc a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTransition(Transition t) {
		// TODO Auto-generated method stub
		
	}
	
}
