package mainPackage;

import java.util.LinkedList;
import java.util.Random;

import Exceptions.NegativeNbTokensException;
import Exceptions.NegativeWeightException;



/**
 * The 'PetriNet' class represents a Petri network with places, transitions, and arcs.
 * It implements the 'PetriNetInterface' interface, providing methods to operate, manipulate and analyze the network.
 */
public class PetriNet implements PetriNetInterface {
	
	private String name;
	private LinkedList<Arc> arcList;
	private LinkedList<Place> placeList;
	private LinkedList<Transition> transitionList;
	private int arcCount;
	private int transitionCount;
	private int placeCount;
	
	/**
	 * Constructs a new empty PetriNet with a specified Name.
	 * @param name The name of the PetriNet.
	 */
	public PetriNet(String name) {
		this.setName(name);
		this.setArcList(new LinkedList<Arc>());
		this.setPlaceList(new LinkedList<Place>());;
		this.setTransitionList(new LinkedList<Transition>());
		arcCount = 0;
		transitionCount = 0;
		placeCount = 0;
	}
	
	/**
	 * Gets the name of this PetriNet.
	 * @return The name of the PetriNet.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of this PetriNet.
	 * @param name The desired name for this PetriNet.
	 */
	private void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the list of Arc of this PetriNet.
	 * @return The list of Arc of the PetriNet.
	 */
	public LinkedList<Arc> getArcList() {
		return arcList;
	}
	
	/**
	 * Sets the list of Arc of this PetriNet.
	 * @param arcList The desired list of Arc for this PetriNet.
	 */
	private void setArcList(LinkedList<Arc> arcList) {
		this.arcList = arcList;
	}
	
	/**
	 * Gets the list of Place of this PetriNet.
	 * @return The list of Place of the PetriNet.
	 */
	public LinkedList<Place> getPlaceList() {
		return placeList;
	}
	
	/**
	 * Sets the list of Place of this PetriNet.
	 * @param arcList The desired list of Place for this PetriNet.
	 */
	private void setPlaceList(LinkedList<Place> placeList) {
		this.placeList = placeList;
	}
	
	/**
	 * Gets the list of Transition of this PetriNet.
	 * @return The list of Transition of the PetriNet.
	 */
	public LinkedList<Transition> getTransitionList() {
		return transitionList;
	}
	
	/**
	 * Sets the list of Transition of this PetriNet.
	 * @param arcList The desired list of Transition for this PetriNet.
	 */
	private void setTransitionList(LinkedList<Transition> transitionList) {
		this.transitionList = transitionList;
	}
	
	/**
	 * Adds a designated type of Arc in this PetriNet, associated with a Place and a Transition.
	 * It also starts the verification and treatment process ensuring that no "double Arc" are existing in the network.
	 * @param type The wanted type of the Arc to be created. This method processes two types : "in" and "out".
	 * @param weight The weight to be associated with the created Arc.
	 * @param place The Place to be associated with the created Arc.
	 * @param transition The Transition to be associated with the created Arc.
	 * @throws Exception
	 * @throws NegativeWeightException
	 */
	public Arc addArc(String type, int weight, Place place, Transition transition) throws Exception, NegativeWeightException{
		if (!(samePetriNet(this, place, transition))) {
			throw new Exception("Can't create an arc if associated place and transition are not from the same PetriNet.");
		}
		int test = treatExistingArc(type, weight, place, transition);
		if (test == 1) {
			throw new Exception("Can't create an arc if there is already an existing zero/emptying one between the same place and transition.");
		}
		if (test==3){
			System.out.println("There was already an arc between the designated place and transition. The weight of the former one has been updated.");
			return findExistingArc(place, transition);
		}
		if (weight<0) {
			throw new NegativeWeightException("WARNING: An arc can not have a negative weight.");
		}
		if (type == "in") {
			if (test == 2) {
				throw new Exception("Can't create an arcIn if there is already an existing arcOut between the same place and transition.");
			}
			ArcIn newArcIn = new ArcIn(arcCount, weight, place, transition);
			this.arcList.add(newArcIn);
			arcCount++;
			return newArcIn;
		}
		else if (type == "out") {
			if (test == 2) {
				throw new Exception("Can't create an arcOut if there is already an existing arcIn between the same place and transition.");
			}
			ArcOut newArcOut = new ArcOut(arcCount, weight, place, transition);
			this.arcList.add(newArcOut);
			arcCount++;
			return newArcOut;
		}
		else {
			throw new Exception("This type of arc does not exist/Not enough arguments");
		}
	}
	
	/**
	 * Adds a designated type of Arc in this PetriNet, associated with a Place and a Transition.
	 * It also starts the verification and treatment process ensuring that no "double Arc" are existing in the network.
	 * @param type The wanted type of the Arc to be created. This method processes two types : "emptying" and "zero".
	 * @param place The Place to be associated with the created Arc.
	 * @param transition The Transition to be associated with the created Arc.
	 * @throws Exception
	 */
	public Arc addArc(String type, Place place, Transition transition) throws Exception {
		if (!(samePetriNet(this, place, transition))) {
			throw new Exception("Can't create an arc if associated place and transition are not from the same PetriNet.");
		}
		if (treatExistingArc(type,-1, place, transition) == 1) {
			throw new Exception("Can't create an emptying/zero arc if there is already an existing arc between the same place and transition.");
		}
		if (type == "emptying") {
			ArcEmptying newArcEmptying = new ArcEmptying(arcCount, place, transition); 
			this.arcList.add(newArcEmptying);
			arcCount++;
			return newArcEmptying;
		}
		else if (type == "zero") {
			ArcZero newArcZero = new ArcZero(arcCount, place, transition);
			this.arcList.add(newArcZero);
			arcCount++;
			return newArcZero;
		}
		else { 
			throw new Exception("This type of arc does not exist/Not enough arguments");
		}
	}
	
	/**
	 * Checks if both designated Place and Transition are from the same designated PetriNet.
	 * @param petriNet The designated PetriNet.
	 * @param place The designated Place.
	 * @param transition The designated Transition.
	 * @return Returns true if both Place and Transition are from the designated PetriNet. False otherwise.
	 */
	public boolean samePetriNet(PetriNet petriNet, Place place, Transition transition) {
		return(petriNet.getPlaceList().contains(place) && petriNet.getTransitionList().contains(transition));
	}
	
	/**
	 * Checks if the Arc to be added to the network produces a "double Arc" situation. It proceeds with the process associated with the situation.
	 * @param type The type of the added Arc. 
	 * @param weight The weight of the added Arc.
	 * @param place The Place of the added Arc.
	 * @param transition The Transition of the added Arc.
	 * @return The case number associated with the situation regarding the "double Arc" situation. 0 if there is no existing Arc already, 1 if the added Arc is a ZeroArc/Emptying Arc, 2 if the added Arc and the existing Arc are not the same type (in/out), 3 if if the added Arc and the existing Arc are the same type (in/out). 
	 */
	public int treatExistingArc(String type, int weight, Place place, Transition transition) {
		Arc testedArc = findExistingArc(place, transition);
		
		if (testedArc == null) {
			return 0;
		}
		
		else if (testedArc instanceof ArcZero || testedArc instanceof ArcEmptying) {
				return 1;
		}
		
		else if (weight == -1) {
			return 1;
		}
		
		else if ((type == "in" && testedArc instanceof ArcOut) || (type == "out" && testedArc instanceof ArcIn)){
			return 2;
		}
		
		else {
			testedArc.setWeight(testedArc.getWeight() + weight);
			return 3;
		}		
	}
	
	/**
	 * Finds if there is already an Arc in the network associated with the designated Place and Transition. If it is the case, returns the Arc.
	 * @param place The Place possibly associated with the Arc.
	 * @param transition The Transition possibly associated with the Arc.
	 * @return The existing Arc if one fits the criteria. Nothing otherwise.
	 */
	public Arc findExistingArc(Place place, Transition transition) {
		for (Arc testedArc : arcList) {
			if (testedArc.getPlace() == place && testedArc.getTransition() == transition) {
				return testedArc;
			}
		}
		return null;
	}
	
	/**
	 * Adds a Place with a designated number of tokens to the network.
	 * @param tokens The number of tokens of the added Place.
	 * @throws NegativeNbTokensException 
	 */
	public Place addPlace(int tokens) throws NegativeNbTokensException {
		Place newPlace = new Place(placeCount, tokens);
		this.placeList.add(newPlace);
		placeCount++;
		return newPlace;
	}

	/**
	 * Adds a Transition to the network.
	 */
	public Transition addTransition() {
		Transition newTransition = new Transition(transitionCount);
		transitionList.add(newTransition);
		transitionCount++;
		return newTransition;
	}

	/**
	 * Removes an Arc of the network. It ensures that the associated Place and Transition are correctly updated.
	 * @param arc The Arc to be removed of the network.
	 */
	public void removeArc(Arc arc) {
		if (arc instanceof ArcIn) {
			arc.getTransition().removeArcIn((ArcIn) arc);
			arc.getPlace().removeArc(arc);		
		}
		else if (arc instanceof ArcOut) {
			arc.getTransition().removeArcOut((ArcOut) arc);
			arc.getPlace().removeArc(arc);
		}
		this.arcList.remove(arc);		
	}
	
	/**
	 * Removes a Place of the network. It ensures that the associated Arc are correctly updated.
	 * @param place The Place to be removed of the network.
	 */
	public void removePlace(Place place) {
		LinkedList<Arc> tempList = (LinkedList<Arc>) place.getArcList().clone();
		for (Arc tempArc : tempList) {
			removeArc(tempArc);
		}
		this.placeList.remove(place);
	}
	
	/**
	 * Removes a Transition of the network. It ensures that the Transition is correctly updated.
	 * @param place The Transition to be removed of the network.
	 */
	public void removeTransition(Transition transition) {
		for (ArcIn arcTemp : transition.getArcInList()) {
			removeArc(arcTemp);
		}
		
		for (ArcOut arcTemp : transition.getArcOutList()) {
			removeArc(arcTemp);
		}
		
		this.transitionList.remove();
		
	}
	
	/**
	 * Fires a step in the network: it draws a random possible Transition of the network and proceeds with its execution.
	 */
	public void fire() throws Exception {
		LinkedList<Transition> possibleTransition = new LinkedList<Transition>();

		for (Transition tempTransition : this.transitionList) {
			if (tempTransition.isDrawable()) {
				possibleTransition.add(tempTransition);
			}
		}
		if (possibleTransition.size() != 0) {
			Transition drawnTransition = possibleTransition.get(new Random().nextInt(possibleTransition.size()));
			
			for (ArcIn tempArc : drawnTransition.getArcInList()) {
				tempArc.startExchange();
			}

			for (ArcOut tempArc : drawnTransition.getArcOutList()) {
				tempArc.startExchange();
			}
		}
	}
	
	public String toString() {
		String res = "";
		String r = "-->";
		String l = "<--";
		res += "---------------Places---------------\n";
		for (Place p : this.placeList) {
			String line =p.toString()+" ";
			for (Arc a : p.getArcList()) {
				if (a instanceof ArcIn) {
					line += r+" "+a.getId();
				}
				if (a instanceof ArcOut) {
					line = (a.getId()+" "+r+" ") + line;
				}
				
			}
			line += "\n";
			res += line;
		}
		res += "------------Transitions-------------\n";
		for (Transition t : this.getTransitionList()) {
			String line = t.toString()+" ";
			String ArcInList = "";
			for (Arc a : t.getArcInList()) {
				ArcInList += a.getId()+" ";
			}
			if (ArcInList != "") {
				ArcInList += r+" ";
			}
			line = ArcInList + line;
			String ArcOutList = "";
			for (Arc a : t.getArcOutList()) {
				ArcOutList += a.getId()+" ";
			}
			if (ArcOutList != "") {
				ArcOutList = r+" " + ArcOutList;
			}
			line += ArcOutList;
			line += "\n";
			res += line;
		}
		res += "----------------Arcs----------------\n";
		for (Arc a : this.getArcList()) {
			if (a instanceof ArcIn) {
				res += a.getTransition().toString() +" "+ l +" "+a.toString()+" "+l+" "+ a.getPlace().getId() +"\n";
			}
			if (a instanceof ArcOut) {
				res += a.getTransition().toString() +" "+ r +" "+a.toString()+" "+r+" "+ a.getPlace().getId() +"\n";
			}
		}
		
		return res;
	}
	
	public static void main(String[] args) throws Exception {
		
		PetriNet pn0 = new PetriNet("");
		
		Place p0 = pn0.addPlace(2);
		Place p1 = pn0.addPlace(0);
		Transition t0 = pn0.addTransition();
		Transition t1 = pn0.addTransition();
		Arc a0 = pn0.addArc("out", 2, p0, t0);
		Arc a1 = pn0.addArc("in", 3, p0, t1);
		Arc a2 = pn0.addArc("in", 1, p1, t0);
		
		
		
		System.out.println(pn0.toString());

		
	}
}
