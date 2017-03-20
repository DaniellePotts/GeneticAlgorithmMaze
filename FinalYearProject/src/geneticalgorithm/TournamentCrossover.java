package geneticalgorithm;

import java.util.Random;

import globals.*;

public class TournamentCrossover {
	private static int tournamentSize = 10;
	public  Population EvolvePop(Population currPop){
		Population newPop = new Population(currPop.PopSize());
		
		int elitismOffSet = 0;
		if(AlgorithmSettings.Elitism){
			elitismOffSet = 1;
		}
		
		for(int i=elitismOffSet;i<currPop.PopSize();i++){
			Gene mother = TornamentSelection(currPop);
			Gene father = TornamentSelection(currPop);
			Gene child = Crossover(mother,father);
			child.ID = i;
			newPop.SetGene(i, child);
		}
		for(int i=elitismOffSet;i<newPop.PopSize();i++){
			Mutate(newPop.GetGene(i));
		}
		
		return newPop;
	}
	
	private  Gene Crossover(Gene partnerA, Gene partnerB){
		Gene child = new Gene();
		for(int i=0;i<child.possibleMovements.size();i++){
			String movement = "";
			if(Math.random() <= AlgorithmSettings.UniformRate){
				movement = partnerA.possibleMovements.get(i).Movement;
			}else{
				movement = partnerB.possibleMovements.get(i).Movement;
			}
			
			child.possibleMovements.get(i).Movement = movement;
		}
		
		return child;
	}
	
	private  Gene TornamentSelection(Population pop){
		Population tournament = new Population(tournamentSize);
		
		for(int i=0;i<tournamentSize;i++){
			int rnd = (int)(Math.random() * pop.PopSize());
			tournament.SetGene(i, pop.GetGene(rnd));
		}
		
		return tournament.GetFittest(tournament);
	}
	
	private static void Mutate(Gene gene){
		String [] movements = new String[]{"L","R","U","D"};
		Random rnd = new Random();
		for(int i=0;i<gene.possibleMovements.size();i++){
			if(Math.random() <= AlgorithmSettings.MutationRate){
				String movement = movements[rnd.nextInt(movements.length)];
				gene.possibleMovements.get(i).Movement = movement;
			}
		}
	}
}
