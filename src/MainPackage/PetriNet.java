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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTransition(LinkedList<ArcIn> arcIn, LinkedList<ArcOut> arcOut) {
		
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
