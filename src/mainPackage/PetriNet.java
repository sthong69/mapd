package mainPackage;

import java.util.LinkedList;
import java.util.Random;

import Exceptions.NegativeWeightException;

public class PetriNet implements PetriNetInterface {
	
	private String name;
	private LinkedList<Arc> arcList;
	private LinkedList<Place> placeList;
	private LinkedList<Transition> transitionList;
	
	public PetriNet(String name) {
		// TODO : faire un toString()
		this.setName(name);
		this.setArcList(new LinkedList<Arc>());
		this.setPlaceList(new LinkedList<Place>());;
		this.setTransitionList(new LinkedList<Transition>());
	}
	
	public String getName() {
		return name;
	}
	
	private void setName(String name) {
		this.name = name;
	}
	
	public LinkedList<Arc> getArcList() {
		return arcList;
	}
	
	private void setArcList(LinkedList<Arc> arcList) {
		this.arcList = arcList;
	}
	
	public LinkedList<Place> getPlaceList() {
		return placeList;
	}
	
	private void setPlaceList(LinkedList<Place> placeList) {
		this.placeList = placeList;
	}
	
	public LinkedList<Transition> getTransitionList() {
		return transitionList;
	}
	
	private void setTransitionList(LinkedList<Transition> transitionList) {
		this.transitionList = transitionList;
	}
	

	public void addArc(String type, int weight, Place p, Transition t) throws Exception, NegativeWeightException{
		if (weight<0) {
			throw new NegativeWeightException("WARNING: An arc can not have a negative weight.");
		}
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

	public void addPlace(int tokens) {
		Place newPlace = new Place(tokens);
		this.placeList.add(newPlace);
	}

	public void addTransition() {
		Transition t = new Transition();
		transitionList.add(t);
	}

	public void removePlace(Place p) {
		for (Arc tempArc : p.getArcList()) {
			removeArc(tempArc);
		}
		this.placeList.remove(p);
	}

	public void removeArc(Arc a) {
		if (a instanceof ArcIn) {
			a.getTransition().removeArcIn((ArcIn) a);
		}
		else if (a instanceof ArcOut) {
			a.getTransition().removeArcOut((ArcOut) a);
		}
		this.arcList.remove(a);		
	}

	public void removeTransition(Transition t) {
		for (ArcIn arcTemp : t.getArcInList()) {
			removeArc(arcTemp);
		}
		
		for (ArcOut arcTemp : t.getArcOutList()) {
			removeArc(arcTemp);
		}
		
		this.transitionList.remove();
		
	}
	
	public void fire() throws Exception {
		LinkedList<Transition> possibleTransition = new LinkedList<Transition>();

		for (Transition tempTransition : this.transitionList) {
			if (tempTransition.isDrawable()) {
				possibleTransition.add(tempTransition);
			}
		}

		Transition drawnTransition = possibleTransition.get(new Random().nextInt(possibleTransition.size()));
		
		for (ArcIn tempArc : drawnTransition.getArcInList()) {
			tempArc.startExchange();
		}

		for (ArcOut tempArc : drawnTransition.getArcOutList()) {
			tempArc.startExchange();
		}
}
	
}
