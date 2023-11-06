package mainPackage;

import java.util.LinkedList;

public class Transition {
	
	private LinkedList<ArcIn> arcInList;
	private LinkedList<ArcOut> arcOutList;
	
	
	public Transition() {
		setArcInList();
		setArcOutList();
	}
	
	public LinkedList<ArcIn> getArcInList() {
		return arcInList;
	}
	
	public void setArcInList() {
		this.arcInList = new LinkedList<ArcIn>();
	}
	
	public LinkedList<ArcOut> getArcOutList() {
		return arcOutList;
	}
	public void setArcOutList() {
		this.arcOutList = new LinkedList<ArcOut>();
	}
	
	public void addArcIn(ArcIn arc) {
		this.arcInList.add(arc);
	}
	
	public void removeArcIn(ArcIn arc) {
		this.arcInList.remove(arc);
	}
	
	public void addArcOut(ArcOut arc) {
		this.arcOutList.add(arc);
	}
	
	public void removeArcOut(ArcOut arc) {
		this.arcOutList.remove(arc);
	}
	
	public boolean isDrawable() {
		int counter;
		int totalWeight;
		LinkedList<Place> treatedPlaces;
		LinkedList<ArcIn> treatedArcs;
		for (ArcIn arc : this.getArcInList()) {
			counter = 0;
			totalWeight = 0;
			treatedPlaces = new LinkedList<Place>();
			treatedArcs = new LinkedList<ArcIn>();
			if (!(treatedPlaces.contains(arc.getPlace()))) {
				treatedPlaces.add(arc.getPlace());
				for (Arc checkedArc : arc.getPlace().getArcList()) {
					if (checkedArc instanceof ArcIn && checkedArc.getTransition() == this) {
						counter += 1;
						treatedArcs.add((ArcIn) checkedArc);
					}
				if (counter == 1 && !(arc.checkAvailability())) {
					return false;
				} else if (counter != 1) {
					for (ArcIn treatedArc : treatedArcs) {
						totalWeight += treatedArc.getWeight();
					}
					if (totalWeight > treatedArcs.getFirst().getPlace().getNbTokens()) {
						return false;
							}
						}
					}
				}
			}
		return true;
	}
	
	public String toString() {
		return "Transition String";
	}
	
}
