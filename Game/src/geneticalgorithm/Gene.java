package geneticalgorithm;

import java.util.ArrayList;
import java.util.List;

import helpers.LoadPossibitilies;

public class Gene implements java.io.Serializable{
	private static final long serialVersionUID = 8528873851249269580L;
	public List<PossibleMovements> possibleMovements;
	public double Fitness;
	public int x, y;
	public int ID;

	public String LastMovement;

	public Gene() {
		possibleMovements = new ArrayList<>();
		LoadPossibitilies loadPoss = new LoadPossibitilies();
		possibleMovements = loadPoss.CreatePossibleMovements();
	}
}