package geneticalgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import globals.AlgorithmSettings;

public class BucketCrossover {
	private List<Gene> MatingPool;

	public void Mate(List<Gene> Population) {
		MatingPool = new ArrayList<>();
		Fitness fitness = new Fitness();
		double maxFitness = fitness.MaxFitness(Population);
		
		for (int i = 0; i < Population.size(); i++) {
			float fitnessNormal = Map(Population.get(i).Fitness,0,maxFitness,0,1);
			int n = (int) (fitnessNormal * 100);

			for (int j = 0; j < n; j++) {
				MatingPool.add(Population.get(i));
			}
		}

		Random rnd = new Random();
		for (int i = 0; i < Population.size(); i++) {
			int a = (int) rnd.nextInt(MatingPool.size());
			int b = (int) rnd.nextInt(MatingPool.size());
			Gene partnerA = MatingPool.get(a);
			Gene partnerB = MatingPool.get(b);
			Gene child = Crossover(partnerA,partnerB);
			child.ID = i;
			Population.set(i, child);
		}
	}
	
	private float Map(double x, double in_min, double in_max, double out_min, long out_max){
		 return (float) ((x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min);
	}
	
	private Gene Crossover(Gene partnerA, Gene partnerB) {
		Random rand = new Random();
		Gene child = new Gene();

		child.possibleMovements = new ArrayList<>();
		int midPoint = rand.nextInt(partnerA.possibleMovements.size()); //possible movements are our genes DNA, so we split this

		for (int i = 0; i < partnerA.possibleMovements.size(); i++) {
			PossibleMovements ps = new PossibleMovements();
			ps.Possibility = partnerA.possibleMovements.get(i).Possibility;
			ps.PreviousMovement =partnerA.possibleMovements.get(i).PreviousMovement;
			
			if (i > midPoint) {
				ps.Movement = partnerA.possibleMovements.get(i).Movement;
			} else {
				ps.Movement = partnerB.possibleMovements.get(i).Movement;
			}
			
			child.possibleMovements.add(ps);
	}

		return Mutate(child,AlgorithmSettings.MutationRate);

	}
	
	private Gene Mutate(Gene child, float mutationRate) {
		Random rand = new Random();
		String[] options = new String[] { "L", "R", "U", "D" };
		for (int i = 0; i < child.possibleMovements.size(); i++) {
			if (rand.nextDouble() < mutationRate) {
				child.possibleMovements.get(i).Movement = options[rand
						.nextInt(options.length)];
			}
		}
		
		return child;
	}
}
