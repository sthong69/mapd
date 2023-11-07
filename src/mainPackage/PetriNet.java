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
	
	public Arc addArc(String type, int weight, Place p, Transition t) throws Exception, NegativeWeightException{
		int test = treatExistingArc(type,weight,p,t);
		if (test == 1) {
			throw new Exception("Can't create an arc if there is already an existing zero/emptying one between the same place and transition.");
		}
		if (test==3){
			System.out.println("There was already an arc between the designated place and transition. The weight of the former one has been updated");
			return findExistingArc(p,t);
		}
		if (weight<0) {
			throw new NegativeWeightException("WARNING: An arc can not have a negative weight.");
		}
		if (type == "in") {
			if (test == 2) {
				throw new Exception("Can't create an arcIn if there is already an existing arcOut between the same place and transition.");
			}
			ArcIn newArcIn = new ArcIn(weight, p, t);
			this.arcList.add(newArcIn);
			return newArcIn;
		}
		else if (type == "out") {
			if (test == 2) {
				throw new Exception("Can't create an arcOut if there is already an existing arcIn between the same place and transition.");
			}
			ArcOut newArcOut = new ArcOut(weight, p, t);
			this.arcList.add(newArcOut);
			return newArcOut;
		}
		else {
			throw new Exception("This type of arc does not exist/Not enough arguments");
		}
	}
	
	public Arc addArc(String type, Place p, Transition t) throws Exception {
		if (treatExistingArc(type,-1,p,t) == 1) {
			throw new Exception("Can't create an emptying/zero arc if there is already an existing arc between the same place and transition.");
		}
		
		if (type == "emptying") {
			ArcEmptying newArcEmptying = new ArcEmptying(p, t); 
			this.arcList.add(newArcEmptying);
			return newArcEmptying;
		}
		else if (type == "zero") {
			ArcZero newArcZero = new ArcZero(p,t);
			this.arcList.add(newArcZero);
			return newArcZero;
		}
		else { 
			throw new Exception("This type of arc does not exist/Not enough arguments");
		}
	}
	
	public int treatExistingArc(String type, int weight, Place p, Transition t) {
		Arc testedArc = findExistingArc(p,t);
		
		if (testedArc == null) {
			return 0;
		}
		
		else if (testedArc instanceof ArcZero || testedArc instanceof ArcEmptying) {
				return 1;
		}
		
		else if (weight == -1) {
			return 1;
		}
		
		else if ((type == "int" && testedArc instanceof ArcOut) || (type == "out" && testedArc instanceof ArcIn)){
			return 2;
		}
		
		else {
			testedArc.setWeight(testedArc.getWeight()+weight);
			return 3;
		}		
	}
	
	public Arc findExistingArc(Place p, Transition t) {
		for (Arc testedArc : arcList) {
			if (testedArc.getPlace() == p && testedArc.getTransition() == t) {
				return testedArc;
			}
		}
		return null;
	}

	public Place addPlace(int tokens) {
		Place newPlace = new Place(tokens);
		this.placeList.add(newPlace);
		return newPlace;
	}

	public Transition addTransition() {
		Transition t = new Transition();
		transitionList.add(t);
		return t;
	}



	public void removeArc(Arc a) {
		if (a instanceof ArcIn) {
			a.getTransition().removeArcIn((ArcIn) a);
			a.getPlace().removeArc(a);		
		}
		else if (a instanceof ArcOut) {
			a.getTransition().removeArcOut((ArcOut) a);
			a.getPlace().removeArc(a);
		}
		this.arcList.remove(a);		
	}
	
	public void removePlace(Place p) {
		LinkedList<Arc> tempList = (LinkedList<Arc>) p.getArcList().clone();
		for (Arc tempArc : tempList) {
			removeArc(tempArc);
		}
		this.placeList.remove(p);
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
