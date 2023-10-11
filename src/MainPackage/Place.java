package MainPackage;

public class Place {
	private int nbTokens;
	
	public Place(int nbTokens) {
		setnbTokens(nbTokens);
	}
	
	private void setnbTokens(int nbTokens) {
		this.nbTokens = nbTokens;
	}
	
	public int getNbTokens() {
		return nbTokens;
	}
	
	public void addTokens(int nbTokensToAdd) {
		nbTokens += nbTokensToAdd;
	}
	
	public void removeTokens(int nbTokensToRemove) throws Exception {
		if (nbTokensToRemove < nbTokens) {
			throw new Exception("Pas assez de jetons.");
		}
		nbTokens -= nbTokensToRemove;
	}
	
	public boolean isEmpty() {
		return (nbTokens == 0);
	}
	
	public String toString() {
		return "Place String";
	}
	
}
