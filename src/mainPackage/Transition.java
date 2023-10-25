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
	
	public void addArcInList(ArcIn arc) {
		this.arcInList.add(arc);
	}
	
	public void removeArcInList(ArcIn arc) {
		this.arcInList.remove(arc);
	}
	
	public void addArcOutList(ArcOut arc) {
		this.arcOutList.add(arc);
	}
	
	public void removeArcOutList(ArcOut arc) {
		this.arcOutList.remove(arc);
	}
	
	public boolean isDrawable() {
		boolean test;
		for (ArcIn arc : this.arcInList) {
			test = arc.checkAvailability();
			if (!test) {
				return false;
			}
		}
		return true;
	}
	
	public String toString() {
		return "Transition String";
	}
	
}
