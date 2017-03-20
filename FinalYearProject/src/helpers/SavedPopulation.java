package helpers;

import java.util.ArrayList;
import java.util.List;

import geneticalgorithm.Gene;
import globals.AlgorithmSettings;

public class SavedPopulation implements java.io.Serializable {
	private static final long serialVersionUID = 5496330269017201727L;
	public List<Gene> Genes;
	public int Generation;
	public double MaxFitness;
	public double AverageFitness;
	public SavedPopulation(int popSize) {
		Generation = AlgorithmSettings.Generation;
		Genes = new ArrayList<>();
		for (int i = 0; i < popSize; i++) {
			Gene newGene = new Gene();
			newGene.ID = i;
			Genes.add(newGene);
		}
	}
}
