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

	public void addArc(String type, int weight, Place p, Transition t) throws Exception {
		if (type == "in") {
			ArcIn newArcIn = new ArcIn(weight, p, t);
			this.arcList.add(newArcIn);
		}
		else if (type == "out") {
			ArcOut newArcOut = new ArcOut(weight, p, t);
			this.arcList.add(newArcOut);
		}
		else {
			throw new Exception("Ce type d'arc n'existe pas/Pas assez d'arguments");
		}
	}
	
	public void addArc(String type, Place p, Transition t) throws Exception {
		if (type == "emptying") {
			ArcEmptying newArcEmptying = new ArcEmptying(p, t); 
			this.arcList.add(newArcEmptying);
		}
		else if (type == "zero") {
			ArcZero newArcZero = new ArcZero(p,t);
			this.arcList.add(newArcZero);
		}
		else { 
			throw new Exception("Ce type d'arc n'existe pas/Trop d'arguments");
		}
	}

	@Override
	public void addPlace(int tokens) {
		Place newPlace = new Place(tokens);
		this.placeList.add(newPlace);
	}

	@Override
	public void addTransition() {
		Transition t = new Transition();
		transitionList.add(t);
	}

	@Override
	public void removePlace(Place p) {
		for (Arc tempArc : p.getArcList()) {
			removeArc(tempArc);
		}
		this.placeList.remove(p);
	}

	@Override
	public void removeArc(Arc a) {
		if (a instanceof ArcIn) {
			a.getTransition().removeArcInList((ArcIn) a);
		}
		else if (a instanceof ArcOut) {
			a.getTransition().removeArcOutList((ArcOut) a);
		}
		this.arcList.remove(a);		
	}

	@Override
	public void removeTransition(Transition t) {
		for (ArcIn arcTemp : t.getArcInList()) {
			removeArc(arcTemp);
		}
		
		for (ArcOut arcTemp : t.getArcOutList()) {
			removeArc(arcTemp);
		}
		
		this.transitionList.remove();
		
	}
	
}
