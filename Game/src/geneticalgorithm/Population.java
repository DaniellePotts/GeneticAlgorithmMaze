package geneticalgorithm;

import java.util.*;

public class Population{
	
	public List<Gene> Genes;
	public int Generation;

	public Population(int popSize) {
		Generation = 1;
		Genes = new ArrayList<>();
		for (int i = 0; i < popSize; i++) {
			Gene newGene = new Gene();
			newGene.ID = i;
			Genes.add(newGene);
		}
	}

	public int PopSize() {
		return Genes.size();
	}

	public Gene GetGene(int index) {
		return Genes.get(index);
	}

	public void SetGene(int index, Gene newGene) {
		if (newGene == null) {
			newGene = new Gene();
		}
		Genes.set(index, newGene);
	}

	public Gene GetFittest(Population pop) {
		Gene fittest = new Gene();

		for (int i = 0; i < pop.PopSize(); i++) {
			if (pop.GetGene(i).Fitness > fittest.Fitness) {
				fittest = pop.GetGene(i);
			}
		}

		return fittest;
	}
}