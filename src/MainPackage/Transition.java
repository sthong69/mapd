package MainPackage;

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
